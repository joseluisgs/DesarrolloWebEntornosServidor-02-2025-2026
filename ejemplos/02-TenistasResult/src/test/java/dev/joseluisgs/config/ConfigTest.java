package dev.joseluisgs.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para Config.
 * Verifica el comportamiento del patrón Singleton y la carga de propiedades/valores por defecto.
 * Referencia: dev.joseluisgs.config.Config
 */
@DisplayName("Tests de configuración Config")
public class ConfigTest {

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("getInstance devuelve siempre la misma instancia (singleton)")
        void getInstanceDevuelveSiempreLaMismaInstancia() {
            Config c1 = Config.getInstance();
            Config c2 = Config.getInstance();

            assertAll("Singleton",
                    () -> assertNotNull(c1),
                    () -> assertSame(c1, c2)
            );
        }

        @Test
        @DisplayName("Lee propiedades desde config.properties correctamente")
        void leePropiedadesDesdeArchivoCorrectamente() {
            Config config = Config.getInstance();

            assertAll("Propiedades leídas",
                    () -> assertNotNull(config.getDatabaseUrl()),
                    () -> assertEquals("jdbc:h2:mem:tenistas;DB_CLOSE_DELAY=-1", config.getDatabaseUrl()),
                    () -> assertEquals(5, config.getCacheSize()),
                    () -> assertTrue(config.isDatabaseInitTables()),
                    () -> assertTrue(config.isDatabaseInitData())
            );
        }

        @Test
        @DisplayName("setResourceNameForTests con recurso de test carga dicho archivo")
        void setResourceNameConRecursoDeTestCargaArchivo() {
            // Usamos un properties específico de test para verificar la carga
            Config.setResourceNameForTests("test-config.properties");
            Config.resetForTests();

            Config config = Config.getInstance();

            assertAll("Propiedades de test",
                    () -> assertEquals("jdbc:h2:mem:tenistas-test;DB_CLOSE_DELAY=-1", config.getDatabaseUrl()),
                    () -> assertEquals(12, config.getCacheSize()),
                    () -> assertFalse(config.isDatabaseInitTables()),
                    () -> assertFalse(config.isDatabaseInitData())
            );

            // limpieza
            Config.setResourceNameForTests("config.properties");
            Config.resetForTests();
        }

        @Test
        @DisplayName("setResourceNameForTests con null usa 'config.properties' por defecto")
        void setResourceNameNullUsaConfigPorDefecto() {
            Config.setResourceNameForTests(null);
            Config.resetForTests();

            Config config = Config.getInstance();

            assertAll("Default por null",
                    () -> assertEquals(5, config.getCacheSize()),
                    () -> assertTrue(config.isDatabaseInitTables()),
                    () -> assertTrue(config.isDatabaseInitData())
            );

            // limpieza
            Config.setResourceNameForTests("config.properties");
            Config.resetForTests();
        }

        @Test
        @DisplayName("setResourceNameForTests con blanco usa 'config.properties' por defecto")
        void setResourceNameBlancoUsaConfigPorDefecto() {
            Config.setResourceNameForTests("   ");
            Config.resetForTests();

            Config config = Config.getInstance();

            assertAll("Default por blanco",
                    () -> assertEquals(5, config.getCacheSize()),
                    () -> assertTrue(config.isDatabaseInitTables()),
                    () -> assertTrue(config.isDatabaseInitData())
            );

            // limpieza
            Config.setResourceNameForTests("config.properties");
            Config.resetForTests();
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("Llamadas repetidas a getInstance no producen null ni nuevas instancias")
        void llamadasRepetidasNoProducenNullNiNuevasInstancias() {
            Config prev = Config.getInstance();
            for (int i = 0; i < 10; i++) {
                Config current = Config.getInstance();
                assertAll("Repetidas",
                        () -> assertNotNull(current),
                        () -> assertSame(prev, current)
                );
            }
        }

        @Test
        @DisplayName("Si no existe el recurso de propiedades se usan valores por defecto")
        void siNoExisteElRecursoUsaValoresPorDefecto() {
            // Forzamos a que no encuentre el archivo y reiniciamos el singleton
            Config.setResourceNameForTests("no-existe.properties");
            Config.resetForTests();

            Config config = Config.getInstance();

            assertAll("Valores por defecto",
                    () -> assertEquals("jdbc:h2:mem:tenistas;DB_CLOSE_DELAY=-1", config.getDatabaseUrl()),
                    () -> assertEquals(5, config.getCacheSize()),
                    () -> assertTrue(config.isDatabaseInitTables()),
                    () -> assertTrue(config.isDatabaseInitData())
            );

            // Dejamos el estado como estaba para no afectar a otros tests
            Config.setResourceNameForTests("config.properties");
            Config.resetForTests();
            assertNotNull(Config.getInstance());
        }

        @Test
        @DisplayName("IOException al cargar properties usa defaults y escribe en System.err")
        void ioExceptionAlCargarUsaDefaultsYEscribeEnSystemErr() {
            // Forzamos el nombre del recurso a uno existente para que el fallo sea por IOException
            Config.setResourceNameForTests("config.properties");
            // Forzamos IOException en el constructor
            Config.setForceIOExceptionForTests(true);
            Config.resetForTests();

            // Capturamos System.err
            java.io.PrintStream errPrev = System.err;
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            System.setErr(new java.io.PrintStream(baos));

            try {
                Config config = Config.getInstance();

                String errSalida = baos.toString();
                assertAll("IOException en carga",
                        () -> assertEquals("jdbc:h2:mem:tenistas;DB_CLOSE_DELAY=-1", config.getDatabaseUrl()),
                        () -> assertEquals(5, config.getCacheSize()),
                        () -> assertTrue(errSalida.contains("Error al cargar el archivo de propiedades"))
                );
            } finally {
                // Restauramos System.err y estado de Config
                System.setErr(errPrev);
                Config.setForceIOExceptionForTests(false);
                Config.setResourceNameForTests("config.properties");
                Config.resetForTests();
                assertNotNull(Config.getInstance());
            }
        }
    }
}
