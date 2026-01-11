package dev.joseluisgs;

import dev.joseluisgs.dao.TenistasDao;
import dev.joseluisgs.database.JdbiManager;
import dev.joseluisgs.errors.TenistaError;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.repository.TenistasRepositoryImpl;
import dev.joseluisgs.service.TenistasRailwayService;
import dev.joseluisgs.service.TenistasRailwayServiceImpl;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.storage.TenistasStorageImpl;
import io.vavr.control.Either;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * Punto de entrada de la aplicación Tenistas.
 * Se encarga de inicializar las dependencias, cargar datos de ejemplo, realizar operaciones CRUD
 * y exportar información a JSON mostrando algunas consultas sobre los datos.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see TenistasRailwayService
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.database.JdbiManager
 */
public class Main {
    /**
     * Método principal de la aplicación.
     * Inicializa la configuración de salida en UTF-8, crea las dependencias del dominio,
     * importa datos desde CSV, realiza operaciones básicas (insertar, actualizar, borrar) y
     * exporta a JSON, además de mostrar ejemplos de consultas con streams.
     *
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        //System.out.println(System.getProperty("file.encoding"));

        System.out.println("🎾🎾 ¡¡Hola Tenistas!! 🎾🎾");

        Instant start = Instant.now();

        // Inicializamos dependencias
        TenistasDao dao = JdbiManager.getInstance().getTenistasDao();
        TenistasRepository repository = new TenistasRepositoryImpl(dao);
        TenistasStorage storage = new TenistasStorageImpl();
        TenistasRailwayService service = new TenistasRailwayServiceImpl(repository, storage);

        // 1️⃣ Cargar datos desde CSV
        System.out.println("1️⃣ Cargando datos del CSV...");
        var csvPath = Paths.get("data", "data01.csv").toString();
        var importResult = service.importFromCsv(csvPath);
        if (importResult.isLeft()) {
            System.err.println("Error cargando CSV: " + importResult.getLeft().getMessage());
        } else {
            var tenistas = service.findAll();
            System.out.println("Tenistas cargados: " + tenistas.size());
            tenistas.forEach(System.out::println);
        }

        // 2️⃣ Insertar, actualizar y borrar un tenista de prueba
        System.out.println("2️⃣ Manipulando un tenista de prueba...");
        Tenista nuevoTenista = Tenista.builder()
                .nombre("Pepe Tenista")
                .pais("España")
                .altura(180)
                .peso(75)
                .puntos(1000)
                .mano(Tenista.Mano.ZURDO)
                .fechaNacimiento(java.time.LocalDate.of(1990, 1, 1))
                .build();

        // Encadenamiento de operaciones
        Either<TenistaError, Boolean> resultadoFinal = service.save(nuevoTenista)
                // Map the specific ValidationError to the parent TenistaError type
                .mapLeft(error -> (TenistaError) error)
                .flatMap(creado -> {
                    System.out.println("Insertando nuevo tenista: " + creado);
                    System.out.println("Tenista creado con ID: " + creado.getId());
                    System.out.println("Actualizando tenista...");
                    creado.setPuntos(1200);
                    return service.update(creado);
                })
                .flatMap(actualizado -> {
                    System.out.println("Tenista actualizado: " + actualizado);
                    System.out.println("Borrando tenista...");
                    // mapLeft to cast NotFound to its parent TenistaError type
                    return service.deleteById(actualizado.getId()).mapLeft(error -> error);
                });

        if (resultadoFinal.isLeft()) {
            System.err.println("Error manipulando tenista de prueba: " + resultadoFinal.getLeft().getMessage());
        } else {
            System.out.println("Tenista borrado: " + resultadoFinal.get());
        }

        // 3️⃣ Exportar datos a JSON
        System.out.println("3️⃣ Exportando datos a JSON...");
        var jsonPath = Paths.get("data", "data01.json").toString();
        var exportResult = service.exportToJson(jsonPath);
        if (exportResult.isLeft()) {
            System.err.println("Error exportando a JSON: " + exportResult.getLeft().getMessage());
        } else {
            System.out.println("Exportación a JSON completada con éxito.");
        }


        // 4️⃣ Consultas con streams
        System.out.println("4️⃣ Consultas con streams:");

        var tenistas = service.findAll();

        // Filtrar tenistas de España, ordenar por puntos y obtener los 5 mejores
        var top5 = tenistas.stream()
                .filter(t -> t.getPais().equalsIgnoreCase("España"))
                .sorted((t1, t2) -> Integer.compare(t2.getPuntos(), t1.getPuntos()))
                .limit(5)
                .toList();

        System.out.println("Top 5 tenistas de España por puntos:");
        top5.forEach(System.out::println);

        // Agrupar los top 5 por mano y calcular número y media de puntos
        var grouped = top5.stream()
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
        System.out.println("Top 5 tenistas de España agrupados por mano con número y media de puntos:");
        grouped.forEach((mano, stats) ->
                System.out.println("Mano: " + mano + ", Número de tenistas: " + stats.count +
                        ", Media de puntos: " + stats.avgPoints)
        );

        Instant end = Instant.now();
        System.out.println("Tiempo de ejecución: " + Duration.between(start, end).toMillis() + " ms");

        System.out.println("🎾🎾 !!Hasta luego Tenistas!! 🎾🎾");
    }
}