package dev.joseluisgs.storage;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para TenistasStorageImpl.
 * Valida la carga desde CSV y el guardado a JSON, así como los escenarios de error.
 * Referencia: dev.joseluisgs.storage.TenistasStorageImpl
 */
@DisplayName("Tests de almacenamiento de TenistasStorageImpl")
public class TenistasStorageImplTest {

    @TempDir
    Path tempDir;

    private TenistasStorageImpl crearStorage() {
        return new TenistasStorageImpl();
    }

    private String crearCsvTemporal(String nombreArchivo, List<String> lineas) throws IOException {
        Path file = tempDir.resolve(nombreArchivo);
        Files.write(file, lineas, StandardCharsets.UTF_8);
        return file.toString();
    }

    private List<String> cabeceraCsv() {
        return List.of("id,nombre,pais,altura,peso,puntos,mano,fecha_nacimiento");
    }

    private List<Tenista> construirTenistasEjemplo() {
        return List.of(
                Tenista.builder().id(1).nombre("Carlos Alcaraz").pais("España").altura(183).peso(74).puntos(9000).mano(Tenista.Mano.DIESTRO).fechaNacimiento(LocalDate.of(2003, 5, 5)).build(),
                Tenista.builder().id(2).nombre("Rafael Nadal").pais("España").altura(185).peso(85).puntos(10000).mano(Tenista.Mano.ZURDO).fechaNacimiento(LocalDate.of(1986, 6, 3)).build()
        );
    }

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("cargar csv válido devuelve lista con datos")
        void cargarCsvValidoDevuelveListaCorrecta() throws Exception {
            TenistasStorageImpl storage = crearStorage();
            // Usamos el archivo de ejemplo del proyecto
            String ruta = Path.of("data", "data01.csv").toString();
            List<Tenista> tenistas = storage.loadData(ruta);
            // Comprobamos algunos campos de la primera fila conocida
            Tenista primero = tenistas.get(0);

            assertAll("Carga CSV y primer registro",
                    () -> assertNotNull(tenistas),
                    () -> assertTrue(tenistas.size() > 0),
                    () -> assertEquals(1, primero.getId()),
                    () -> assertEquals("Novak Djokovic", primero.getNombre()),
                    () -> assertEquals("Serbia", primero.getPais()),
                    () -> assertEquals(188, primero.getAltura()),
                    () -> assertEquals(77, primero.getPeso()),
                    () -> assertEquals(12030, primero.getPuntos()),
                    () -> assertEquals(Tenista.Mano.DIESTRO, primero.getMano()),
                    () -> assertEquals(LocalDate.of(1987, 5, 22), primero.getFechaNacimiento())
            );
        }

        @Test
        @DisplayName("cargar csv con tenista inválido lo filtra")
        void cargarCsvConTenistaInvalidoLoFiltra() throws Exception {
            TenistasStorageImpl storage = crearStorage();
            // Creamos CSV temporal con 1 válido y 1 inválido (fecha futura)
            String lineaValida = "10,Jugador Valido,España,180,75,1000,DIESTRO,1990-01-01";
            String lineaInvalida = "11,Jugador Futuro,España,180,75,1000,DIESTRO," + LocalDate.now().plusDays(1);
            String ruta = crearCsvTemporal("mi-tenistas.csv", List.of(
                    cabeceraCsv().get(0),
                    lineaValida,
                    lineaInvalida
            ));
            List<Tenista> tenistas = storage.loadData(ruta);
            assertAll("Filtrado de inválidos",
                    () -> assertEquals(1, tenistas.size(), "Debe filtrar el tenista inválido"),
                    () -> assertEquals(10, tenistas.get(0).getId())
            );
        }

        @Test
        @DisplayName("guardar json válido devuelve el número y escribe contenido")
        void guardarJsonValidoDevuelveNumeroYEscribeContenido() throws Exception {
            TenistasStorageImpl storage = crearStorage();
            List<Tenista> datos = construirTenistasEjemplo();
            Path destino = tempDir.resolve("tenistas.json");
            int escritos = storage.saveData(datos, destino.toString());
            String contenido = Files.readString(destino, StandardCharsets.UTF_8);

            assertAll("Guardado JSON",
                    () -> assertEquals(datos.size(), escritos),
                    () -> assertTrue(Files.exists(destino)),
                    () -> assertTrue(contenido.contains("\"nombre\"")),
                    () -> assertTrue(contenido.contains("Carlos Alcaraz")),
                    () -> assertTrue(contenido.contains("fecha_nacimiento"))
            );
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("cargar path nulo lanza excepción")
        void cargarPathNuloLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            assertThrows(TenistaException.StorageException.class, () -> storage.loadData(null));
        }

        @Test
        @DisplayName("cargar path vacío lanza excepción")
        void cargarPathVacioLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            assertThrows(TenistaException.StorageException.class, () -> storage.loadData(""));
        }

        @Test
        @DisplayName("cargar extensión no csv lanza excepción")
        void cargarExtensionNoCsvLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            assertThrows(TenistaException.StorageException.class, () -> storage.loadData(tempDir.resolve("archivo.txt").toString()));
        }

        @Test
        @DisplayName("cargar fichero inexistente lanza excepción")
        void cargarFicheroInexistenteLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            String ruta = tempDir.resolve("no-existe.csv").toString();
            assertThrows(TenistaException.StorageException.class, () -> storage.loadData(ruta));
        }

        @Test
        @DisplayName("cargar csv con formato inválido lanza TenistaException.StorageException")
        void cargarCsvConFormatoInvalidoLanzaExcepcion() throws Exception {
            TenistasStorageImpl storage = crearStorage();
            // Mano inválida provoca IllegalArgumentException en valueOf -> capturado por catch(Exception)
            String lineaMala = "1,Jugador Malo,España,180,75,1000,MANO_RARA,1990-01-01";
            String ruta = crearCsvTemporal("mal.csv", List.of(
                    cabeceraCsv().get(0),
                    lineaMala
            ));
            assertThrows(TenistaException.StorageException.class, () -> storage.loadData(ruta));
        }

        @Test
        @DisplayName("cargar csv que produce IOException lanza TenistaException.StorageException")
        void cargarCsvQueProduceIOExceptionLanzaExcepcion() throws Exception {
            TenistasStorageImpl storage = crearStorage();
            // Creamos un directorio con extensión .csv para forzar IOException al leer líneas
            Path dirComoCsv = tempDir.resolve("directorio.csv");
            Files.createDirectory(dirComoCsv);

            TenistaException.StorageException ex = assertThrows(TenistaException.StorageException.class, () -> storage.loadData(dirComoCsv.toString()));
            assertAll("Excepción de IO al cargar",
                    () -> assertNotNull(ex),
                    () -> assertTrue(ex.getMessage().toLowerCase().contains("leer el archivo"))
            );
        }

        @Test
        @DisplayName("guardar path nulo lanza excepción")
        void guardarPathNuloLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            assertThrows(TenistaException.StorageException.class, () -> storage.saveData(List.of(), null));
        }

        @Test
        @DisplayName("guardar path vacío lanza excepción")
        void guardarPathVacioLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            assertThrows(TenistaException.StorageException.class, () -> storage.saveData(List.of(), ""));
        }

        @Test
        @DisplayName("guardar extensión no json lanza excepción")
        void guardarExtensionNoJsonLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            assertThrows(TenistaException.StorageException.class, () -> storage.saveData(List.of(), tempDir.resolve("salida.txt").toString()));
        }

        @Test
        @DisplayName("guardar en directorio inexistente lanza TenistaException.StorageException")
        void guardarEnDirectorioInexistenteLanzaExcepcion() {
            TenistasStorageImpl storage = crearStorage();
            // Ruta a un fichero dentro de un subdirectorio que no existe
            Path subdir = tempDir.resolve("no-existe");
            Path destino = subdir.resolve("tenistas.json");
            assertThrows(TenistaException.StorageException.class, () -> storage.saveData(List.of(), destino.toString()));
        }
    }
}
