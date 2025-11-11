package dev.joseluisgs;

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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MainMoreAsync {
    public static void main(String[] args) {
        // 🔤 Forzamos UTF-8 en la consola para soportar acentos y emojis
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("🎾🎾 ¡¡Hola Tenistas!! 🎾🎾");

        Instant start = Instant.now();

        // 🛠 Inicialización de dependencias
        TenistasRepository repository = new TenistasRepositoryImpl(
                JdbiManager.getInstance().getTenistasDao()
        );
        TenistasStorage storage = new TenistasStorageImpl();
        TenistasServiceAsync service = new TenistasServiceAsyncImpl(repository, storage);

        // 📂 Rutas de archivos
        var csv01Path = Paths.get("data", "data01.csv").toString();
        var csv02Path = Paths.get("data", "data02.csv").toString();
        var jsonPath = Paths.get("data", "data01.json").toString();

        // 🔗 Flujo de operaciones con encadenamiento
        // CompletableFuture<Void> pipeline = service.importFromCsvAsync(csvPath)  --> Si solo es un fichero

        // Crear una CompletableFuture para cada archivo CSV
        var future1 = service.importFromCsvAsync(csv01Path);
        var future2 = service.importFromCsvAsync(csv02Path);

        // Combinar ambas futures para esperar a que ambas finalicen
        CompletableFuture<Void> pipeline = CompletableFuture.allOf(future1, future2);

        // 1️⃣ Importar CSVs
        pipeline.thenRun(() -> System.out.println("✅ CSV importado correctamente"))
                .exceptionally(ex -> {
                    System.err.println("❌ Error importando CSV: " + ex.getCause().getMessage());
                    return null;
                })

                // 2️⃣ Obtener todos los tenistas después de importar
                .thenCompose(v -> service.findAllAsync())
                .thenAccept(tenistas -> {
                    System.out.println("✅ Tenistas cargados: " + tenistas.size());
                    tenistas.forEach(System.out::println);
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error obteniendo tenistas: " + ex.getCause().getMessage());
                    return null;
                })

                // 3️⃣ Insertar → actualizar → borrar un tenista de prueba
                .thenCompose(v -> {
                    Tenista nuevo = Tenista.builder()
                            .nombre("Pepe Tenista")
                            .pais("España")
                            .altura(180)
                            .peso(75)
                            .puntos(1000)
                            .mano(Tenista.Mano.ZURDO)
                            .fechaNacimiento(java.time.LocalDate.of(1990, 1, 1))
                            .build();

                    // Insertar el tenista
                    return service.saveAsync(nuevo)
                            .thenCompose(creado -> {
                                System.out.println("✅ Tenista creado con ID: " + creado.getId());

                                // Actualizar el tenista
                                creado.setPuntos(1200);
                                return service.updateAsync(creado)
                                        .thenCompose(actualizado -> {
                                            System.out.println("✅ Tenista actualizado: " + actualizado);

                                            // Borrar el tenista
                                            return service.deleteByIdAsync(actualizado.getId())
                                                    .thenAccept(borrado -> {
                                                        if (borrado) {
                                                            System.out.println("✅ Tenista borrado con ID: " + actualizado.getId());
                                                        }
                                                    });
                                        });
                            })
                            .exceptionally(ex -> {
                                System.err.println("❌ Error en CRUD: " + ex.getCause().getMessage());
                                return null;
                            });
                })

                // 4️⃣ Exportar a JSON después del CRUD
                .thenCompose(v -> service.exportToJsonAsync(jsonPath))
                .thenRun(() -> System.out.println("✅ JSON exportado correctamente"))
                .exceptionally(ex -> {
                    System.err.println("❌ Error exportando JSON: " + ex.getCause().getMessage());
                    return null;
                })

                // 5️⃣ Consultas con Streams después de exportar
                .thenCompose(v -> service.findAllAsync())
                .thenAccept(tenistas -> {
                    // Top 5 de España
                    var top5 = tenistas.parallelStream()
                            .filter(t -> t.getPais().equalsIgnoreCase("España"))
                            .sorted((t1, t2) -> Integer.compare(t2.getPuntos(), t1.getPuntos()))
                            .limit(5)
                            .toList();

                    System.out.println("🏆 Top 5 tenistas de España por puntos:");
                    top5.forEach(System.out::println);

                    // Agrupados por mano
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

                    System.out.println("✋ Top 5 agrupados por mano y estadísticas:");
                    grouped.forEach((mano, stats) ->
                            System.out.println("Mano: " + mano +
                                    ", Número de tenistas: " + stats.count +
                                    ", Media de puntos: " + stats.avgPoints)
                    );
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error en consultas: " + ex.getCause().getMessage());
                    return null;
                });

        // 🚦 Esperamos al final de TODO el pipeline (una sola vez)
        pipeline.join();

        Instant end = Instant.now();
        System.out.println("Tiempo de ejecución: " + Duration.between(start, end).toMillis() + " ms");

        System.out.println("🎾🎾 ¡¡Hasta luego Tenistas!! 🎾🎾");
    }
}
