package dev.joseluisgs;

import dev.joseluisgs.dao.TenistasDao;
import dev.joseluisgs.database.JdbiManager;
import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.repository.TenistasRepositoryImpl;
import dev.joseluisgs.service.TenistasService;
import dev.joseluisgs.service.TenistasServiceImpl;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.storage.TenistasStorageImpl;

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
 * @see dev.joseluisgs.service.TenistasService
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
        TenistasService service = new TenistasServiceImpl(repository, storage);

        // 1️⃣ Cargar datos desde CSV
        try {
            System.out.println("1\uFE0F⃣ Cargando datos del CSV...");
            var csvPath = Paths.get("data", "data01.csv").toString();
            service.importFromCsv(csvPath);

            var tenistas = service.findAll();
            System.out.println("Tenistas cargados: " + tenistas.size());
            tenistas.forEach(System.out::println);
        } catch (TenistaException.StorageException | TenistaException.ValidationException e) {
            System.err.println("Error cargando CSV: " + e.getMessage());
        }

        // 2️⃣ Insertar, actualizar y borrar un tenista de prueba
        System.out.println("2\uFE0F⃣ Manipulando un tenista de prueba...");
        Tenista nuevoTenista = Tenista.builder()
                .nombre("Pepe Tenista")
                .pais("España")
                .altura(180)
                .peso(75)
                .puntos(1000)
                .mano(Tenista.Mano.ZURDO)
                .fechaNacimiento(java.time.LocalDate.of(1990, 1, 1))
                .build();

        try {
            System.out.println("Insertando nuevo tenista: " + nuevoTenista);
            var creado = service.save(nuevoTenista);
            System.out.println("Tenista creado con ID: " + creado.getId());

            System.out.println("Actualizando tenista...");
            creado.setPuntos(1200);
            var actualizado = service.update(creado);
            System.out.println("Tenista actualizado: " + actualizado);

            System.out.println("Borrando tenista...");
            var borrado = service.deleteById(actualizado.getId());
            System.out.println("Tenista borrado: " + borrado);
        } catch (TenistaException.NotFoundException | TenistaException.ValidationException e) {
            System.err.println("Error manipulando tenista de prueba: " + e.getMessage());
        }

        // 3️⃣ Exportar datos a JSON
        try {
            System.out.println("3\uFE0F⃣ Exportando datos a JSON...");
            var jsonPath = Paths.get("data", "data01.json").toString();
            service.exportToJson(jsonPath);
        } catch (TenistaException.StorageException e) {
            System.err.println("Error exportando a JSON: " + e.getMessage());
        }

        // 4️⃣ Consultas con streams
        System.out.println("4\uFE0F⃣ Consultas con streams:");


        // Implementar consultas con streams con la información de tenistas

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