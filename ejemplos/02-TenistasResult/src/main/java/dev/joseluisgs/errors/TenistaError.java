package dev.joseluisgs.errors;


/**
 * Clase base para los errores relacionados con los tenistas.
 * Esta clase es sellada y solo permite su extensión por un conjunto controlado de subclases.
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 */
public sealed class TenistaError permits TenistaError.NotFound, TenistaError.StorageError, TenistaError.ValidationError {
    private final String message;

    public TenistaError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static final class NotFound extends TenistaError {
        public NotFound(String message) {
            super(message);
        }
    }

    public static final class StorageError extends TenistaError {
        public StorageError(String message) {
            super(message);
        }
    }

    public static final class ValidationError extends TenistaError {
        public ValidationError(String message) {
            super(message);
        }
    }
}