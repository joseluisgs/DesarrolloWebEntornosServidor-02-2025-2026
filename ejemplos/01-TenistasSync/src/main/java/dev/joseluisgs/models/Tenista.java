package dev.joseluisgs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Modelo de dominio que representa a un Tenista con sus atributos principales.
 * Incluye anotaciones de Lombok para generar código boilerplate y anotaciones de Jackson
 * para controlar la serialización/deserialización JSON.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.validator.TenistaValidator
 * @see dev.joseluisgs.mappers.TenistaMapper
 */
@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera el constructor sin parámetros
@AllArgsConstructor // Genera el constructor con todos los parámetros
@Builder // Genera el patrón builder
@JsonPropertyOrder({"id", "nombre", "pais", "altura", "peso", "puntos", "mano", "fecha_nacimiento"})
// Orden de las propiedades en JSON
public class Tenista {
    /**
     * Valor por defecto para indicar que el tenista aún no tiene ID persistido.
     */
    public static final long NEW_TENISTA_ID = -1;

    private long id;
    private String nombre;
    private String pais;
    private int altura;
    private int peso;
    private int puntos;
    private Mano mano;

    @JsonProperty("fecha_nacimiento") // Mapea la propiedad JSON "fecha_nacimiento" al campo fechaNacimiento
    private LocalDate fechaNacimiento;

    /**
     * Mano hábil del tenista.
     */
    public enum Mano {
        DIESTRO,
        ZURDO,
        OTRO
    }

}
