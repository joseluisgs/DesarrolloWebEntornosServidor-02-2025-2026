package dev.joseluisgs.validator;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;

import java.time.LocalDate;

/**
 * Utilidad para validar instancias de Tenista antes de su uso en la aplicación.
 * Proporciona validaciones de campos obligatorios, rangos y coherencia de datos.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.models.Tenista
 * @see dev.joseluisgs.exceptions.TenistaException.ValidationException
 */
public class TenistaValidator {

    /**
     * Constructor privado para evitar la instanciación de la clase de utilidades.
     */
    private TenistaValidator() {
        // Evitar instanciación
    }

    /**
     * Valida un objeto Tenista.
     *
     * @param tenista Tenista a validar
     * @throws TenistaException.ValidationException si algún campo es inválido
     */
    public static void validate(Tenista tenista) throws TenistaException.ValidationException {
        if (tenista == null) {
            throw new TenistaException.ValidationException("El tenista no puede ser nulo");
        }

        if (tenista.getNombre() == null || tenista.getNombre().isBlank()) {
            throw new TenistaException.ValidationException("El nombre del tenista no puede estar vacío");
        }

        if (tenista.getPais() == null || tenista.getPais().isBlank()) {
            throw new TenistaException.ValidationException("El país del tenista no puede estar vacío");
        }

        if (tenista.getAltura() <= 0) {
            throw new TenistaException.ValidationException("La altura debe ser mayor que 0");
        }

        if (tenista.getPeso() <= 0) {
            throw new TenistaException.ValidationException("El peso debe ser mayor que 0");
        }

        if (tenista.getPuntos() < 0) {
            throw new TenistaException.ValidationException("Los puntos no pueden ser negativos");
        }

        // Faltan validaciones para que la mano no sea otro valor que los definidos en la enumeración
        if (tenista.getMano() == null || tenista.getMano() == Tenista.Mano.OTRO) {
            throw new TenistaException.ValidationException("La mano del tenista debe estar definida (DIESTRO, ZURDO)");
        }

        if (tenista.getFechaNacimiento() == null) {
            throw new TenistaException.ValidationException("La fecha de nacimiento no puede ser nula");
        }

        if (tenista.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new TenistaException.ValidationException("La fecha de nacimiento no puede ser futura");
        }
    }
}