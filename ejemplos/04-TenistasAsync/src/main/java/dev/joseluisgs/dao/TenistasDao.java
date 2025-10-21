package dev.joseluisgs.dao;


import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

/**
 * DAO para la entidad TenistaEntity.
 * Define las operaciones CRUD y consultas auxiliares sobre la tabla tenistas mediante JDBI.
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 * @see dev.joseluisgs.dao.TenistaEntity
 * @see dev.joseluisgs.database.JdbiManager
 */
@RegisterConstructorMapper(TenistaEntity.class) //si quieres mapear por constructor
public interface TenistasDao {

    /**
     * Obtiene todos los registros de la tabla tenistas.
     *
     * @return Lista completa de TenistaEntity
     */
    @SqlQuery("SELECT * FROM tenistas")
    List<TenistaEntity> findAll();

    /**
     * Busca un tenista por su identificador.
     *
     * @param id Identificador del tenista
     * @return Optional con la entidad si existe, vacío en caso contrario
     */
    @SqlQuery("SELECT * FROM tenistas WHERE id = :id")
    Optional<TenistaEntity> findById(@Bind("id") long id);

    /**
     * Inserta un nuevo tenista.
     *
     * @param tenista Entidad a insertar
     * @return ID generado por la base de datos
     */
    @SqlUpdate("INSERT INTO tenistas (nombre, pais, altura, peso, puntos, mano, fecha_nacimiento) " +
            "VALUES (:nombre, :pais, :altura, :peso, :puntos, :mano, :fechaNacimiento)")
    @GetGeneratedKeys
    long save(@BindBean TenistaEntity tenista);

    /**
     * Actualiza un tenista existente.
     *
     * @param tenista Entidad con los nuevos valores (debe incluir id)
     * @return Número de filas actualizadas
     */
    @SqlUpdate("UPDATE tenistas SET nombre = :nombre, pais = :pais, altura = :altura, peso = :peso, " +
            "puntos = :puntos, mano = :mano, fecha_nacimiento = :fechaNacimiento WHERE id = :id")
    int update(@BindBean TenistaEntity tenista);

    /**
     * Elimina un tenista por su id.
     *
     * @param id Identificador del tenista
     * @return Número de filas eliminadas
     */
    @SqlUpdate("DELETE FROM tenistas WHERE id = :id")
    int delete(@Bind("id") long id);

    /**
     * Obtiene un subconjunto paginado de tenistas.
     *
     * @param limit  Límite de filas a devolver
     * @param offset Desplazamiento inicial
     * @return Lista de entidades
     */
    @SqlQuery("SELECT * FROM tenistas LIMIT :limit OFFSET :offset")
    List<TenistaEntity> findAll(@Bind("limit") int limit, @Bind("offset") int offset);

    /**
     * Busca tenistas por nombre (LIKE, case-insensitive).
     *
     * @param nombre Patrón de nombre
     * @return Lista de coincidencias
     */
    @SqlQuery("SELECT * FROM tenistas WHERE LOWER(nombre) LIKE LOWER(:nombre)")
    List<TenistaEntity> findByName(@Bind("nombre") String nombre);

    /**
     * Busca tenistas por país exacto.
     *
     * @param pais País
     * @return Lista de coincidencias
     */
    @SqlQuery("SELECT * FROM tenistas WHERE pais = :pais")
    List<TenistaEntity> findByPais(@Bind("pais") String pais);

    /**
     * Calcula la media de puntos.
     *
     * @return Media de puntos
     */
    @SqlQuery("SELECT AVG(puntos) FROM tenistas")
    double avgPuntos();

    /**
     * Cuenta el total de tenistas.
     *
     * @return Número total de registros
     */
    @SqlQuery("SELECT COUNT(*) FROM tenistas")
    int count();

    /**
     * Obtiene el máximo de puntos.
     *
     * @return Máximo de puntos
     */
    @SqlQuery("SELECT MAX(puntos) FROM tenistas")
    int maxPuntos();

    /**
     * Obtiene el mínimo de puntos.
     *
     * @return Mínimo de puntos
     */
    @SqlQuery("SELECT MIN(puntos) FROM tenistas")
    int minPuntos();
}