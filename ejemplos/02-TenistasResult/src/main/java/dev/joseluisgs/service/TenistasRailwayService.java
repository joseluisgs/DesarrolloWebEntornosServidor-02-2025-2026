package dev.joseluisgs.service;

import dev.joseluisgs.errors.TenistaError;
import dev.joseluisgs.models.Tenista;
import io.vavr.control.Either;

import java.util.List;

/**
 * Servicio de dominio para gestionar operaciones de Tenista, incluyendo caché,
 * validaciones y coordinación con repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see TenistasRailwayServiceImpl
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 */
public interface TenistasRailwayService {
    /**
     * Obtiene todos los tenistas.
     *
     * @return Lista completa
     */
    List<Tenista> findAll();

    /**
     * Obtiene un tenista por id, consultando la caché si es posible.
     *
     * @param id Identificador
     * @return Either con el Tenista encontrado o un error de tipo NotFound.
     */
    Either<TenistaError.NotFound, Tenista> findById(long id);

    /**
     * Valida y guarda un nuevo tenista.
     *
     * @param tenista Tenista a guardar
     * @return Either con el Tenista guardado o un error de tipo ValidationError.
     */
    Either<TenistaError.ValidationError, Tenista> save(Tenista tenista);

    /**
     * Valida y actualiza un tenista existente.
     *
     * @param tenista Tenista con cambios
     * @return Either con el Tenista actualizado o un error de tipo NotFound o ValidationError.
     */
    Either<TenistaError, Tenista> update(Tenista tenista);

    /**
     * Elimina un tenista por su id.
     *
     * @param id Identificador
     * @return Either con true si se eliminó o un error de tipo NotFound.
     */
    Either<TenistaError.NotFound, Boolean> deleteById(long id);

    /**
     * Importa tenistas desde un CSV y los guarda tras validarlos.
     *
     * @param filePath Ruta al archivo CSV
     * @return Either vacío si la operación fue exitosa o un error de tipo StorageError o ValidationError.
     */
    Either<TenistaError, Void> importFromCsv(String filePath);

    /**
     * Exporta los tenistas actuales a un archivo JSON.
     *
     * @param filePath Ruta destino del JSON
     * @return Either vacío si la operación fue exitosa o un error de tipo StorageError.
     */
    Either<TenistaError.StorageError, Void> exportToJson(String filePath);
}