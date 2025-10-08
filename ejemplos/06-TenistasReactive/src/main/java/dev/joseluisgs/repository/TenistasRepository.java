package dev.joseluisgs.repository;

import dev.joseluisgs.models.Tenista;

import java.util.List;
import java.util.Optional;

/**
 * Contrato del repositorio para gestionar Tenista en la capa de acceso a datos.
 *
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.repository.TenistasRepositoryImpl
 * @see dev.joseluisgs.dao.TenistasDao
 * @see dev.joseluisgs.models.Tenista
 */
public interface TenistasRepository {
    /**
     * Recupera todos los tenistas.
     * @return Lista de tenistas
     */
    List<Tenista> findAll();

    /**
     * Busca un tenista por su id.
     * @param id Identificador del tenista
     * @return Optional con el tenista si existe
     */
    Optional<Tenista> findById(long id);

    /**
     * Guarda un nuevo tenista.
     * @param tenista Tenista a guardar
     * @return Tenista guardado con id asignado
     */
    Tenista save(Tenista tenista);

    /**
     * Actualiza un tenista existente.
     * @param tenista Tenista con cambios
     * @return Optional con el tenista actualizado o vacío si no existe
     */
    Optional<Tenista> update(Tenista tenista);

    /**
     * Elimina un tenista por su id.
     * @param id Identificador del tenista
     * @return true si se borró, false en caso contrario
     */
    boolean deleteById(long id);
}