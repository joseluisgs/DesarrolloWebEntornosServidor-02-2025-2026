package dev.joseluisgs.database;

import dev.joseluisgs.config.Config;
import dev.joseluisgs.dao.TenistasDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

/**
 * Gestor singleton para la configuración y acceso a JDBI.
 * Inicializa la conexión, plugins y, opcionalmente, crea tablas y datos desde scripts SQL.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.config.Config
 * @see dev.joseluisgs.dao.TenistasDao
 */
public class JdbiManager {
    private static JdbiManager instance;
    private final Jdbi jdbi;
    private final Logger logger = LoggerFactory.getLogger(JdbiManager.class); // Logger

    /**
     * Constructor privado que configura JDBI y ejecuta scripts de inicialización si procede.
     */
    private JdbiManager() {
        logger.info("Creando instancia de JdbiManager y configurando JDBI.");
        String url = Config.getInstance().getDatabaseUrl(); // Obtener la URL de la configuración
        boolean initTables = Config.getInstance().isDatabaseInitTables(); // ¿Debe inicializar las tablas en la base de datos?
        boolean initData = Config.getInstance().isDatabaseInitData(); // ¿Debe inicializar los datos en la base de datos?

        this.jdbi = Jdbi.create(url); // Crear la instancia de JDBI

        // Se instalan los plugins, necesarios para usar SqlObject
        jdbi.installPlugin(new SqlObjectPlugin());


        // La URL de H2 en memoria permite crear tablas y cargar datos en tiempo de ejecución
        // Se asume que los archivos tables.sql y data.sql están en src/main/resources

        // ¿Creamos tablas y datos?
        if (initTables) {
            logger.info("Inicializando tablas en la base de datos.");
            executeSqlScriptFromResources("tables.sql");
        }
        if (initData) {
            logger.info("Inicializando datos en la base de datos.");
            executeSqlScriptFromResources("data.sql");
        }
    }

    /**
     * Solo para tests: reinicia el singleton para forzar nueva configuración.
     */
    static synchronized void resetForTests() {
        instance = null;
    }

    /**
     * Devuelve la única instancia de JdbiManager (patrón Singleton).
     *
     * @return Instancia única
     */
    public static synchronized JdbiManager getInstance() {
        if (instance == null) {
            instance = new JdbiManager();
        }
        return instance;
    }

    /**
     * Obtiene un DAO de Tenistas a demanda usando JDBI.
     *
     * @return Implementación dinámica de TenistasDao
     */
    // Método para obtener el DAO
    public TenistasDao getTenistasDao() {
        // Devuelve un DAO que se obtiene de JDBI a través de onDemand
        // Esto garantiza que solo se crea un DAO una vez y lo devuelve en cada llamada
        // Esto es útil cuando se necesita crear un DAO en un contexto de ejecución diferente (por ejemplo, en una prueba unitaria)
        // En este caso, se utiliza el patrón Singleton para garantizar que solo exista una instancia de la clase JdbiManager y su DAO en todo el programa.
        // Este patrón también puede ser utilizado para crear instancias de otros DAOs o cualquier otro tipo de DAO en la aplicación.
        logger.info("Obteniendo DAO de tenistas.");
        return jdbi.onDemand(TenistasDao.class);
    }

    /**
     * Ejecuta un script SQL desde un recurso.
     *
     * @param resourcePath Ruta del recurso en el classpath
     */
    private void executeSqlScriptFromResources(String resourcePath) {
        logger.debug("Cargando script SQL desde recursos: " + resourcePath);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String script = reader.lines().collect(Collectors.joining("\n"));
            // Ejecuta el script en un solo handle
            jdbi.useHandle(handle -> handle.createScript(script).execute());
        } catch (Exception e) {
            logger.error("Error al ejecutar el script SQL desde recursos: {}", resourcePath, e);
            logger.error("Error: {}", e.getMessage());
        }
    }
}