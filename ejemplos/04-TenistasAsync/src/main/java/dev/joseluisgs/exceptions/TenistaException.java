package dev.joseluisgs.exceptions;


/**
 * Clase base para las excepciones relacionadas con los tenistas.
 * Esta clase es sellada y solo permite su extensión por un conjunto controlado de subclases.
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 */
public sealed class TenistaException extends Exception
        permits TenistaException.NotFoundException, TenistaException.StorageException, TenistaException.ValidationException {
    /**
     * Crea una excepción de dominio de tenistas.
     *
     * @param message Mensaje descriptivo del error
     */
    public TenistaException(String message) {
        super(message);
    }

    public static final class NotFoundException extends TenistaException {
        /**
         * Crea la excepción de tenista no encontrado.
         *
         * @param message Mensaje descriptivo
         */
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static final class StorageException extends TenistaException {
        /**
         * Crea una excepción de almacenamiento.
         *
         * @param message Mensaje descriptivo
         */
        public StorageException(String message) {
            super(message);
        }
    }

    public static final class ValidationException extends TenistaException {
        /**
         * Crea una excepción de validación.
         *
         * @param message Mensaje descriptivo
         */
        public ValidationException(String message) {
            super(message);
        }
    }
}