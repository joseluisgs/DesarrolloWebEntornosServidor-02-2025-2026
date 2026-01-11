package dev.joseluisgs;

import dev.joseluisgs.dao.TenistasDao;
import dev.joseluisgs.database.JdbiManager;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.repository.TenistasRepositoryImpl;
import dev.joseluisgs.service.TenistasServiceAsync;
import dev.joseluisgs.service.TenistasServiceAsyncImpl;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.storage.TenistasStorageImpl;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Punto de entrada de la aplicación Tenistas.
 * Se encarga de inicializar las dependencias, cargar datos de ejemplo, realizar operaciones CRUD
 * y exportar información a JSON mostrando algunas consultas sobre los datos.
 * todo de manera asíncrona
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 * @see dev.joseluisgs.service.TenistasServiceAsync
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.database.JdbiManager
 */
public class MainAsync {
    /**
     * Método principal de la aplicación.
     * Inicializa la configuración de salida en UTF-8, crea las dependencias del dominio,
     * importa datos desde CSV, realiza operaciones básicas (insertar, actualizar, borrar) y
     * exporta a JSON, además de mostrar ejemplos de consultas con streams.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Forzamos la codificación UTF-8 para que la consola soporte acentos y emojis
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("🎾🎾 ¡¡Hola Tenistas!! 🎾🎾");

        Instant start = Instant.now();

        // Inicializamos dependencias del dominio
        TenistasDao dao = JdbiManager.getInstance().getTenistasDao();
        TenistasRepository repository = new TenistasRepositoryImpl(dao);
        TenistasStorage storage = new TenistasStorageImpl();
        TenistasServiceAsync service = new TenistasServiceAsyncImpl(repository, storage);

        // Ruta al CSV y JSON
        var csv01Path = Paths.get("data", "data01.csv").toString();
        var csv02Path = Paths.get("data", "data02.csv").toString();
        var jsonPath = Paths.get("data", "data01.json").toString();

        // 1️⃣ Importar CSV de manera asíncrona
        System.out.println("1️⃣ Cargando datos del CSV...");


        // Solo una promesa a la vez, descomentar la que se quiera probar

        /*service.importFromCsvAsync(csvPath)
                .thenRun(() -> System.out.println("✅ CSV importado correctamente"))
                .exceptionally(ex -> { // Captura cualquier error durante la importación
                    System.err.println("❌ Error importando CSV: " + ex.getCause().getMessage());
                    return null;
                })
                .join(); // Bloquea aquí hasta que la importación finalice*/


        // Varios fichros en paralelo, ejemplo

        // Crear una CompletableFuture para cada archivo CSV
        var future1 = service.importFromCsvAsync(csv01Path);
        var future2 = service.importFromCsvAsync(csv02Path);

        // Combinar ambas futures para esperar a que ambas finalicen
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        // Agregar manejo de resultados y errores una vez que ambas operaciones hayan terminado
        allFutures.thenRun(() -> System.out.println("✅ Ambos CSVs importados correctamente"))
                .exceptionally(ex -> {
                    System.err.println("❌ Error importando uno o ambos CSVs: " + ex.getCause().getMessage());
                    return null;
                })
                .join(); // Bloquea hasta que ambas operaciones finalicen

        // 2️⃣ Obtener todos los tenistas cargados
        System.out.println("2️⃣ Obteniendo todos los tenistas...");

        List<Tenista> tenistas = service.findAllAsync()
                .exceptionally(ex -> {
                    System.err.println("❌ Error obteniendo tenistas: " + ex.getCause().getMessage());
                    return List.of(); // Devolver lista vacía si hay error
                })
                .join(); // Bloquea hasta que los tenistas estén disponibles

        System.out.println("Tenistas cargados: " + tenistas.size());
        tenistas.forEach(System.out::println);

        // 3️⃣ Insertar, actualizar y borrar un tenista de prueba
        System.out.println("3️⃣ Manipulando un tenista de prueba...");
        Tenista nuevoTenista = Tenista.builder()
                .nombre("Pepe Tenista")
                .pais("España")
                .altura(180)
                .peso(75)
                .puntos(1000)
                .mano(Tenista.Mano.ZURDO)
                .fechaNacimiento(java.time.LocalDate.of(1990, 1, 1))
                .build();

        // Insertar
        Tenista creado = service.saveAsync(nuevoTenista)
                .exceptionally(ex -> { // Manejo de errores de validación
                    System.err.println("❌ Error creando tenista: " + ex.getCause().getMessage());
                    return null;
                })
                .join(); // Espera a que se guarde

        if (creado != null) {
            System.out.println("✅ Tenista creado con ID: " + creado.getId());

            // Actualizar
            creado.setPuntos(1200);
            Tenista actualizado = service.updateAsync(creado)
                    .exceptionally(ex -> {
                        System.err.println("❌ Error actualizando tenista: " + ex.getCause().getMessage());
                        return null;
                    })
                    .join();
            if (actualizado != null) {
                System.out.println("✅ Tenista actualizado: " + actualizado);

                // Borrar
                Boolean borrado = service.deleteByIdAsync(actualizado.getId())
                        .exceptionally(ex -> {
                            System.err.println("❌ Error borrando tenista: " + ex.getCause().getMessage());
                            return false;
                        })
                        .join();
                if (borrado) System.out.println("✅ Tenista borrado con ID: " + actualizado.getId());
            }
        }

        // 4️⃣ Exportar datos a JSON
        System.out.println("4️⃣ Exportando datos a JSON...");
        service.exportToJsonAsync(jsonPath)
                .thenRun(() -> System.out.println("✅ JSON exportado correctamente"))
                .exceptionally(ex -> {
                    System.err.println("❌ Error exportando JSON: " + ex.getCause().getMessage());
                    return null;
                })
                .join(); // Espera a que finalice la exportación

        // 5️⃣ Consultas con streams
        System.out.println("5️⃣ Consultas con streams:");

        var top5 = tenistas.parallelStream()
                .filter(t -> t.getPais().equalsIgnoreCase("España"))
                .sorted((t1, t2) -> Integer.compare(t2.getPuntos(), t1.getPuntos()))
                .limit(5)
                .toList();

        System.out.println("🏆 Top 5 tenistas de España por puntos:");
        top5.forEach(System.out::println);

        var grouped = top5.parallelStream()
                .collect(Collectors.groupingBy(
                        Tenista::getMano,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> new Object() {
                                    final int count = list.size();
                                    final double avgPoints = list.stream()
                                            .mapToInt(Tenista::getPuntos)
                                            .average()
                                            .orElse(0);
                                }
                        )
                ));

        System.out.println("✋ Top 5 tenistas de España agrupados por mano y estadísticas:");
        grouped.forEach((mano, stats) ->
                System.out.println("Mano: " + mano + ", Número de tenistas: " + stats.count +
                        ", Media de puntos: " + stats.avgPoints)
        );

        Instant end = Instant.now();
        System.out.println("Tiempo de ejecución: " + Duration.between(start, end).toMillis() + " ms");

        System.out.println("🎾🎾 ¡¡Hasta luego Tenistas!! 🎾🎾");
    }
}