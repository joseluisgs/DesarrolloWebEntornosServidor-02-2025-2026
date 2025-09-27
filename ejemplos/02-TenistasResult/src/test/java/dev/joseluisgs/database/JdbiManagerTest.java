package dev.joseluisgs.database;

import dev.joseluisgs.config.ConfigTestHelper;
import dev.joseluisgs.dao.TenistaEntity;
import dev.joseluisgs.dao.TenistasDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para JdbiManager.
 * Comprueba el patrón Singleton y la inicialización de tablas/datos mediante scripts.
 * Referencia: dev.joseluisgs.database.JdbiManager
 */
@DisplayName("Tests de base de datos JdbiManager")
public class JdbiManagerTest {

    private TenistasDao obtenerDao() {
        return JdbiManager.getInstance().getTenistasDao();
    }

    private TenistaEntity construirEntity(String nombre) {
        return TenistaEntity.builder()
                // id por defecto (-1) para inserción
                .nombre(nombre)
                .pais("España")
                .altura(180)
                .peso(75)
                .puntos(1000)
                .mano("DIESTRO")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .build();
    }

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("getInstance devuelve siempre la misma instancia (singleton)")
        void getInstanceDevuelveSiempreLaMismaInstancia() {
            JdbiManager m1 = JdbiManager.getInstance();
            JdbiManager m2 = JdbiManager.getInstance();

            assertAll("Singleton",
                    () -> assertNotNull(m1),
                    () -> assertSame(m1, m2)
            );
        }

        @Test
        @DisplayName("getTenistasDao devuelve un DAO funcional (tablas y datos iniciales)")
        void getTenistasDaoDevuelveDaoFuncional() {
            TenistasDao dao = obtenerDao();

            int total = dao.count();
            List<TenistaEntity> lista = dao.findAll();

            assertAll("DAO funcional",
                    () -> assertTrue(total >= 0),
                    () -> assertNotNull(lista)
            );
        }

        @Test
        @DisplayName("Flujo CRUD: insertar, consultar, actualizar y borrar con TenistasDao")
        void flujoCrudInsertarConsultarActualizarBorrar() {
            TenistasDao dao = obtenerDao();

            // Insertar
            long idGenerado = dao.save(construirEntity("Jugador Test"));
            assertTrue(idGenerado > 0, "Debe generar un id positivo");

            // Consultar por id
            TenistaEntity recuperado = dao.findById(idGenerado).orElse(null);
            assertNotNull(recuperado, "Debe recuperar el insertado");

            // Actualizar
            TenistaEntity actualizar = TenistaEntity.builder()
                    .id(idGenerado)
                    .nombre("Jugador Actualizado")
                    .pais("España")
                    .altura(181)
                    .peso(76)
                    .puntos(1100)
                    .mano("ZURDO")
                    .fechaNacimiento(LocalDate.of(1991, 2, 2))
                    .build();
            int filas = dao.update(actualizar);

            TenistaEntity trasUpdate = dao.findById(idGenerado).orElse(null);

            // Borrar
            int borradas = dao.delete(idGenerado);

            assertAll("CRUD DAO",
                    () -> assertEquals(1, filas, "Debe actualizar 1 fila"),
                    () -> assertNotNull(trasUpdate),
                    () -> assertEquals("Jugador Actualizado", trasUpdate.getNombre()),
                    () -> assertEquals(1, borradas, "Debe borrar 1 fila"),
                    () -> assertTrue(dao.findById(idGenerado).isEmpty(), "Ya no debe existir")
            );
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("Ejecutar script inexistente no lanza excepción (se registra el error)")
        void ejecutarScriptInexistenteNoLanzaExcepcion() throws Exception {
            // Usamos reflexión para invocar el método privado con un recurso que no existe
            JdbiManager manager = JdbiManager.getInstance();
            Method method = JdbiManager.class.getDeclaredMethod("executeSqlScriptFromResources", String.class);
            method.setAccessible(true);

            assertDoesNotThrow(() -> {
                try {
                    method.invoke(manager, "no-existe.sql");
                } catch (Exception e) {
                    // Si el método privado lanza excepción, se re-lanza como cause
                    if (e.getCause() != null) throw (RuntimeException) new RuntimeException(e.getCause());
                    throw new RuntimeException(e);
                }
            });
        }

        @Test
        @DisplayName("Con initTables=false e initData=false no se inicializa y las consultas fallan")
        void conFlagsEnFalseNoInicializaYConsultasFalla() {
            // Configuramos Config para que NO inicialice tablas ni datos
            ConfigTestHelper.setResourceName("test-config.properties"); // en test-config están ambos a false
            ConfigTestHelper.reset();

            // Reiniciamos el singleton de JdbiManager para que relea la nueva config
            JdbiManager.resetForTests();

            try {
                TenistasDao dao = JdbiManager.getInstance().getTenistasDao();
                // Al no existir tablas, cualquier consulta debería fallar
                assertThrows(Exception.class, dao::count);
            } finally {
                // Restauramos el estado para no afectar a otros tests
                ConfigTestHelper.setResourceName("config.properties");
                ConfigTestHelper.reset();
                JdbiManager.resetForTests();
                // Re-creamos una instancia normal para el resto de pruebas
                assertNotNull(JdbiManager.getInstance());
            }
        }
    }
}
