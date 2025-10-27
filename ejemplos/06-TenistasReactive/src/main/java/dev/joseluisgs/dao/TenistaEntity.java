package dev.joseluisgs.dao;

import dev.joseluisgs.models.Tenista;
import lombok.Builder;
import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

import java.time.LocalDate;

/**
 * Entidad de persistencia para la tabla tenistas.
 * Representa cómo se almacenan los datos del tenista en la base de datos.
 *
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.models.Tenista
 * @see dev.joseluisgs.dao.TenistasDao
 */
@Data
@Builder
public class TenistaEntity {

    // Valor por defecto para nuevos tenistas
    @Builder.Default
    private long id = Tenista.NEW_TENISTA_ID;

    private String nombre;

    private String pais;

    private int altura;

    private int peso;

    private int puntos;

    private String mano; // En BD lo almacenamos como texto (DIESTRO, ZURDO, OTRO)

    @ColumnName("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /**
     * Constructor explícito para JDBI que permite mapear resultados de consultas a la entidad.
     *
     * @param id               Identificador del tenista
     * @param nombre           Nombre del tenista
     * @param pais             País del tenista
     * @param altura           Altura en centímetros
     * @param peso             Peso en kilogramos
     * @param puntos           Puntos del ranking
     * @param mano             Mano hábil como texto (DIESTRO, ZURDO, OTRO)
     * @param fechaNacimiento  Fecha de nacimiento
     */
    @JdbiConstructor
    public TenistaEntity(
            @ColumnName("id") long id,
            @ColumnName("nombre") String nombre,
            @ColumnName("pais") String pais,
            @ColumnName("altura") int altura,
            @ColumnName("peso") int peso,
            @ColumnName("puntos") int puntos,
            @ColumnName("mano") String mano,
            @ColumnName("fecha_nacimiento") LocalDate fechaNacimiento
    ) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.altura = altura;
        this.peso = peso;
        this.puntos = puntos;
        this.mano = mano;
        this.fechaNacimiento = fechaNacimiento;
    }
}