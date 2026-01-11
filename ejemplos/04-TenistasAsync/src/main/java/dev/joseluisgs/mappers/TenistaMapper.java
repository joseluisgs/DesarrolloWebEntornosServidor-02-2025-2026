package dev.joseluisgs.mappers;

import dev.joseluisgs.dao.TenistaEntity;
import dev.joseluisgs.models.Tenista;

/**
 * Clase de utilidades para convertir entre el modelo de dominio Tenista y la entidad de persistencia TenistaEntity.
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 * @see dev.joseluisgs.models.Tenista
 * @see dev.joseluisgs.dao.TenistaEntity
 */
public class TenistaMapper {

    /**
     * Constructor privado para evitar la instanciación.
     */
    private TenistaMapper() {
        // Evitar instanciación
    }

    /**
     * Convierte un Tenista (modelo de dominio) a TenistaEntity (para persistencia).
     *
     * @param tenista Objeto de dominio a convertir; si es null devuelve null
     * @return Entidad equivalente para la capa de persistencia o null si el parámetro es null
     */
    public static TenistaEntity toEntity(Tenista tenista) {
        if (tenista == null) return null;

        return TenistaEntity.builder()
                .id(tenista.getId()) // si es -1, el DAO lo ignorará en insert
                .nombre(tenista.getNombre())
                .pais(tenista.getPais())
                .altura(tenista.getAltura())
                .peso(tenista.getPeso())
                .puntos(tenista.getPuntos())
                .mano(tenista.getMano().name()) // enum -> String
                .fechaNacimiento(tenista.getFechaNacimiento())
                .build();
    }

    /**
     * Convierte un TenistaEntity (BD) a Tenista (modelo de dominio).
     *
     * @param entity Entidad recuperada de la base de datos; si es null devuelve null
     * @return Objeto de dominio equivalente o null si el parámetro es null
     */
    public static Tenista fromEntity(TenistaEntity entity) {
        if (entity == null) return null;

        return Tenista.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .pais(entity.getPais())
                .altura(entity.getAltura())
                .peso(entity.getPeso())
                .puntos(entity.getPuntos())
                .mano(Tenista.Mano.valueOf(entity.getMano().toUpperCase())) // String -> enum
                .fechaNacimiento(entity.getFechaNacimiento())
                .build();
    }
}