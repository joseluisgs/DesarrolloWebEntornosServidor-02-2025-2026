package dev.joseluisgs;

import dev.joseluisgs.dao.TenistasDao;
import dev.joseluisgs.database.JdbiManager;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.notifications.Notification;
import dev.joseluisgs.notifications.NotificationService;
import dev.joseluisgs.notifications.NotificationServiceImpl;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.repository.TenistasRepositoryImpl;
import dev.joseluisgs.service.TenistasReactiveService;
import dev.joseluisgs.service.TenistasReactiveServiceImpl;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.storage.TenistasStorageImpl;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;

/**
 * Punto de entrada de la aplicación Tenistas.
 * Se encarga de inicializar las dependencias, cargar datos de ejemplo, realizar operaciones CRUD
 * y exportar información a JSON mostrando algunas consultas sobre los datos.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see TenistasReactiveService
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.database.JdbiManager
 */
public class Main {

    /**
     * Método principal de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Configura la salida para que se muestren correctamente los caracteres especiales.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("🎾🎾 ¡¡Hola Tenistas!! 🎾🎾");

        Instant start = Instant.now();
        // 🚀 Una "bolsa" para guardar todas las suscripciones y poder cancelarlas de golpe al final.
        CompositeDisposable disposables = new CompositeDisposable();

        // 📝 Inicializamos las dependencias
        TenistasDao dao = JdbiManager.getInstance().getTenistasDao();
        TenistasRepository repository = new TenistasRepositoryImpl(dao);
        TenistasStorage storage = new TenistasStorageImpl();
        NotificationService<Tenista> notificacionesService = new NotificationServiceImpl<>();
        TenistasReactiveService service = new TenistasReactiveServiceImpl(repository, storage, notificacionesService);


        // 🚀 Suscripción al servicio de notificaciones
        // Añadimos las suscripciones a nuestra bolsa de 'disposables'.
        disposables.add(notificacionesService.getNotificationsSubject()
                .filter(notificacion -> notificacion.getTipo() == Notification.Tipo.ACTUALIZADO)
                .subscribe(notificacion -> System.out.println("🔔 Notificación recibida (filtrada por ACTUALIZADO): " + notificacion)));

        disposables.add(notificacionesService.getNotificationsSubject()
                .subscribe(notificacion -> System.out.println("🔥 NOTIFICACIÓN RECIBIDA EN GENERAL: " + notificacion)));

        // 🧠 Optimizamos: Usamos replay().autoConnect() para compartir el flujo de datos.
        // Esto previene múltiples llamadas a la base de datos al realizar varias consultas.
        // El Observable se ejecuta una sola vez y su resultado se comparte con todos los suscriptores.
        Observable<Tenista> tenistasFlow = service.findAll()
                .replay()
                .autoConnect();

        // 🚀 Definimos y encadenamos el flujo reactivo completo
        Completable reactiveFlow = Completable.complete()
                // 1️⃣ Cargar datos desde CSV
                // andThen: ejecuta el siguiente flujo solo cuando el anterior se ha completado.

                // Solo si queremos un fichero CSV de ejemplo
                /* .andThen(Completable.fromAction(() -> System.out.println("1️⃣ Cargando datos del CSV...")))
                 .andThen(service.importFromCsv(Paths.get("data", "data01.csv").toString())
                         .doOnNext(tenista -> System.out.println("Importado: " + tenista.getNombre())) // doOnNext: muestra cada tenista mientras se procesa el flujo.
                         .ignoreElements() // ignoreElements: convierte el Observable<Tenista> en un Completable.
                 )
                 .andThen(Completable.fromAction(() -> System.out.println("\n✅ Datos del CSV cargados y procesados correctamente.")))*/

                // 1️⃣ Cargar datos desde CSV en paralelo
                .andThen(Completable.fromAction(() -> System.out.println("1️⃣ Cargando datos de múltiples CSV en paralelo...")))
                // mergeArray: combina múltiples Completables y los ejecuta en paralelo.
                .andThen(Completable.mergeArray(
                        // Flujo para importar el primer archivo
                        service.importFromCsv(Paths.get("data", "data01.csv").toString())
                                .doOnNext(tenista -> System.out.println("Importado (01): " + tenista.getNombre()))
                                .ignoreElements(), // Convierte el Observable en Completable

                        // Flujo para importar el segundo archivo
                        service.importFromCsv(Paths.get("data", "data02.csv").toString())
                                .doOnNext(tenista -> System.out.println("Importado (02): " + tenista.getNombre()))
                                .ignoreElements() // Convierte el Observable en Completable
                ))
                .andThen(Completable.fromAction(() -> System.out.println("\n✅ Datos de los CSV cargados y procesados correctamente.")))

                // 2️⃣ Consultas reactivas sobre el flujo de datos
                .andThen(Completable.fromAction(() -> System.out.println("\n2️⃣ Consultas reactivas (operadores de flujo):")))
                // Top 3 tenistas por puntos de cualquier país
                .andThen(tenistasFlow
                        .sorted((t1, t2) -> Integer.compare(t2.getPuntos(), t1.getPuntos()))
                        .take(3) // take: limita el flujo a los primeros 3 elementos.
                        .doOnNext(t -> System.out.println("Top 3: " + t.getNombre() + " - " + t.getPuntos()))
                        .ignoreElements()
                )
                // Filtra tenistas españoles y cuenta el total.
                .andThen(tenistasFlow
                        .filter(t -> t.getPais().equalsIgnoreCase("España")) // filter: deja pasar solo los tenistas que cumplen la condición.
                        .doOnNext(t -> System.out.println("Tenista español encontrado: " + t.getNombre()))
                        .toList() // toList: acumula todos los elementos en una única lista.
                        .doOnSuccess(espanoles -> System.out.println("Número de tenistas españoles: " + espanoles.size()))
                        .ignoreElement()
                )
                // Agrupa a los tenistas por país para mostrar estadísticas.
                .andThen(tenistasFlow
                        .groupBy(Tenista::getPais) // groupBy: divide el flujo en Observables más pequeños por cada país.
                        .flatMapSingle(group -> group.toList() // flatMapSingle: aplana los grupos y convierte cada uno en una lista.
                                .map(tenistasDelPais -> "País: " + group.getKey() + ", Número de tenistas: " + tenistasDelPais.size()))
                        .doOnNext(System.out::println)
                        .ignoreElements()
                )
                // Calcula el total de puntos de todos los tenistas
                .andThen(tenistasFlow
                        .map(Tenista::getPuntos) // map: transforma cada tenista en sus puntos.
                        .reduce(0, Integer::sum) // reduce: acumula los puntos en un solo valor.
                        .doOnSuccess(total -> System.out.println("Total de puntos de todos los tenistas: " + total))
                        .ignoreElement()
                )
                // Encuentra al tenista con más puntos usando reduce.
                .andThen(tenistasFlow
                        .reduce(new Tenista(), (acc, tenista) -> tenista.getPuntos() > acc.getPuntos() ? tenista : acc)
                        .doOnSuccess(top -> System.out.println("Tenista con más puntos: " + top.getNombre() + " con " + top.getPuntos() + " puntos."))
                        .ignoreElement()
                )
                .andThen(Completable.fromAction(() -> System.out.println("\n✅ Consultas reactivas finalizadas.")))

                // 3️⃣ Manipular un tenista de prueba (CRUD)
                .andThen(Completable.fromAction(() -> System.out.println("\n3️⃣ Manipulando un tenista de prueba...")))
                .andThen(service.save(Tenista.builder()
                                .nombre("Pepe Tenista")
                                .pais("España")
                                .altura(180)
                                .peso(75)
                                .puntos(1000)
                                .mano(Tenista.Mano.ZURDO)
                                .fechaNacimiento(java.time.LocalDate.of(1990, 1, 1))
                                .build())
                        .doOnSuccess(creado -> System.out.println("Tenista creado con ID: " + creado.getId()))
                        .flatMap(creado -> { // flatMap: encadena operaciones que devuelven un nuevo flujo.
                            System.out.println("Actualizando tenista...");
                            creado.setPuntos(1200);
                            return service.update(creado);
                        })
                        .doOnSuccess(actualizado -> System.out.println("Tenista actualizado: " + actualizado))
                        .flatMap(actualizado -> {
                            System.out.println("Borrando tenista...");
                            return service.deleteById(actualizado.getId());
                        })
                        .doOnSuccess(borrado -> System.out.println("Tenista borrado: " + borrado))
                        .ignoreElement()
                )
                .andThen(Completable.fromAction(() -> System.out.println("\n✅ Operaciones CRUD finalizadas.")))

                // 4️⃣ Exportar datos a JSON
                .andThen(Completable.fromAction(() -> System.out.println("\n4️⃣ Exportando datos a JSON...")))
                .andThen(service.exportToJson(Paths.get("data", "data01.json").toString())
                        .doOnSuccess(tenistas -> System.out.println("Exportación a JSON finalizada."))
                        .ignoreElement()
                )

                // 5️⃣ Consultas finales con Streams
                .andThen(Completable.fromAction(() -> System.out.println("\n5️⃣ Consultas con Streams:")))
                .andThen(tenistasFlow
                        .toList() // toList: convierte el flujo de Observables en una única lista de tenistas para usar con Streams.
                        .doOnSuccess(tenistas -> {
                            var top5 = tenistas.stream()
                                    .filter(t -> t.getPais().equalsIgnoreCase("España"))
                                    .sorted((t1, t2) -> Integer.compare(t2.getPuntos(), t1.getPuntos()))
                                    .limit(5)
                                    .toList();

                            System.out.println("Top 5 tenistas de España por puntos:");
                            top5.forEach(System.out::println);

                            var grouped = top5.stream()
                                    .collect(java.util.stream.Collectors.groupingBy(
                                            Tenista::getMano,
                                            java.util.stream.Collectors.collectingAndThen(
                                                    java.util.stream.Collectors.toList(),
                                                    list -> new Object() {
                                                        final int count = list.size();
                                                        final double avgPoints = list.stream()
                                                                .mapToInt(Tenista::getPuntos)
                                                                .average()
                                                                .orElse(0);
                                                    }
                                            )
                                    ));
                            System.out.println("Top 5 tenistas de España agrupados por mano con número y media de puntos:");
                            grouped.forEach((mano, stats) ->
                                    System.out.println("Mano: " + mano + ", Número de tenistas: " + stats.count + ", Media de puntos: " + stats.avgPoints));
                        })
                        .ignoreElement()
                );

        // 🏁 Suscripción y manejo final del flujo completo.
        // subscribeOn: el flujo completo se ejecuta en un hilo de E/S, no en el principal.
        reactiveFlow.subscribeOn(Schedulers.io())
                // doOnError: si cualquier operación del flujo falla, se ejecuta este bloque.
                .doOnError(throwable -> System.err.println("Error en la ejecución del flujo: " + throwable.getMessage()))
                // doFinally: se ejecuta siempre, tanto si hay éxito como si hay error, al final del flujo.
                // ⚠️ Aquí es donde limpiamos las suscripciones de las notificaciones
                .doFinally(() -> {
                    disposables.dispose(); // 🚀 ¡IMPORTANTE! Cancelamos todas las suscripciones de golpe.
                    Instant end = Instant.now();
                    System.out.println("\n🏁 Flujo reactivo finalizado en: " + Duration.between(start, end).toMillis() + " ms");
                    System.out.println("🎾🎾 ¡¡Hasta luego Tenistas!! 🎾🎾");
                })
                // blockingAwait: este método bloquea el hilo principal de la aplicación (main)
                // de forma segura hasta que el flujo reactivo se completa. Es esencial para
                // aplicaciones de consola asíncronas.
                .blockingAwait();
    }
}