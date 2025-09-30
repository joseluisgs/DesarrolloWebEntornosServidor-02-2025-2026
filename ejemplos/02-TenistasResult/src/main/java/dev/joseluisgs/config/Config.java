package dev.joseluisgs.config;

import dev.joseluisgs.service.TenistasRailwayServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuración de la aplicación (patrón Singleton).
 * Carga propiedades desde un archivo del classpath y expone valores de configuración
 * como la URL de la base de datos, tamaño de caché y flags de inicialización.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.database.JdbiManager
 * @see TenistasRailwayServiceImpl
 */
public class Config {
    private static Config instance; // Singleton instance
    private static String resourceName = "config.properties"; // Nombre del recurso a cargar (sobrescribible en tests)
    private static boolean forceIOExceptionForTests = false; // Solo para tests
    private final Properties properties;
    private final Logger logger = LoggerFactory.getLogger(Config.class); // Logger

    /**
     * Constructor privado que carga el fichero de propiedades o usa valores por defecto.
     */
    private Config() {
        logger.info("Creando instancia de la clase Config.");
        this.properties = new Properties();

        // Cargar propiedades desde el archivo indicado
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName)) {
            if (forceIOExceptionForTests) {
                throw new IOException("Excepción forzada para tests");
            }
            if (input == null) {
                logger.warn("No se encontró el archivo %s. Se usarán valores por defecto.".formatted(resourceName));
                setDefaults();
            } else {
                logger.info("Se cargó el archivo de propiedades correctamente: {}", resourceName);
                properties.load(input);
            }
        } catch (IOException ex) {
            logger.error("Error al cargar el archivo de propiedades. Se usarán valores por defecto.", ex);
            System.err.println("Error al cargar el archivo de propiedades. Se usarán valores por defecto.");
            setDefaults();
        }
    }

    /**
     * Devuelve la instancia única de configuración.
     *
     * @return Singleton de Config
     */
    public static synchronized Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Reinicia el singleton (solo para pruebas).
     */
    // Métodos auxiliares solo para tests (package-private)
    static synchronized void resetForTests() {
        instance = null;
    }

    /**
     * Establece el nombre del recurso de properties (solo pruebas). Si es null o blanco, usa "config.properties".
     *
     * @param newResourceName Nombre del recurso a utilizar
     */
    static synchronized void setResourceNameForTests(String newResourceName) {
        resourceName = newResourceName == null || newResourceName.isBlank() ? "config.properties" : newResourceName;
    }

    /**
     * Fuerza una IOException durante la carga (solo pruebas).
     *
     * @param value true para forzar la excepción
     */
    static synchronized void setForceIOExceptionForTests(boolean value) {
        forceIOExceptionForTests = value;
    }

    /**
     * Establece valores por defecto cuando no se puede leer el properties.
     */
    private void setDefaults() {
        // Valores por defecto que tendrá el properties si no se encuentra el archivo
        properties.setProperty("database.url", "jdbc:h2:mem:tenistas;DB_CLOSE_DELAY=-1");
        // Aquí podrías añadir más propiedades por defecto si fueran necesarias
        // properties.setProperty("property.name", "default.value");
        properties.setProperty("cache.size", "5");
        properties.setProperty("database.init.tables", "true");
        properties.setProperty("database.init.data", "true");
    }

    /**
     * Obtiene la URL de la base de datos.
     *
     * @return Cadena con la URL
     */
    // Método para obtener la URL de la base de datos
    public String getDatabaseUrl() {
        return properties.getProperty("database.url"); // Si existe, devuelve la propiedad cargada, si no, el valor por defecto
    }

    /**
     * Obtiene el tamaño de la caché de la aplicación.
     *
     * @return tamaño de caché
     */
    // Método para obtener el tamaño de la caché
    public int getCacheSize() {
        return Integer.parseInt(properties.getProperty("cache.size")); // Valor por defecto
    }

    /**
     * Indica si deben inicializarse las tablas.
     *
     * @return true si se deben crear tablas
     */
    public boolean isDatabaseInitTables() {
        return Boolean.parseBoolean(properties.getProperty("database.init.tables"));
    }

    /**
     * Indica si deben cargarse datos iniciales.
     *
     * @return true si se deben insertar datos
     */
    public boolean isDatabaseInitData() {
        return Boolean.parseBoolean(properties.getProperty("database.init.data"));
    }
}