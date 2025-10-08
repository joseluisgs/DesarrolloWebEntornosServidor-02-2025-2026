package dev.joseluisgs.config;

/**
 * Utilidad de pruebas para facilitar el acceso a métodos de test de Config.
 * Referencia: dev.joseluisgs.config.Config
 *
 * @author joseluisgs
 */
public class ConfigTestHelper {
    public static void reset() {
        Config.resetForTests();
    }

    public static void setResourceName(String resource) {
        Config.setResourceNameForTests(resource);
    }

    public static void setForceIOException(boolean value) {
        Config.setForceIOExceptionForTests(value);
    }
}
