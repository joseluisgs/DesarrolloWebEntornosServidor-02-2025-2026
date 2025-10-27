package dev.joseluisgs.service;

import dev.joseluisgs.models.Tenista;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Servicio de dominio para gestionar operaciones de Tenista de manera asíncrona,
 * incluyendo caché, validaciones y coordinación con repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 * @see TenistasServiceAsyncImpl
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 */
public interface TenistasServiceAsync {

    /**
     * Obtiene todos los tenistas de manera asíncrona.
     *
     * @return CompletableFuture con la lista completa
     */
    CompletableFuture<List<Tenista>> findAllAsync();

    /**
     * Obtiene un tenista por id de manera asíncrona.
     *
     * @param id Identificador
     * @return CompletableFuture con el tenista encontrado
     */
    CompletableFuture<Tenista> findByIdAsync(long id);

    /**
     * Valida y guarda un nuevo tenista de manera asíncrona.
     *
     * @param tenista Tenista a guardar
     * @return CompletableFuture con el tenista guardado
     */
    CompletableFuture<Tenista> saveAsync(Tenista tenista);

    /**
     * Valida y actualiza un tenista existente de manera asíncrona.
     *
     * @param tenista Tenista con cambios
     * @return CompletableFuture con el tenista actualizado
     */
    CompletableFuture<Tenista> updateAsync(Tenista tenista);

    /**
     * Elimina un tenista por su id de manera asíncrona.
     *
     * @param id Identificador
     * @return CompletableFuture con true si se eliminó
     */
    CompletableFuture<Boolean> deleteByIdAsync(long id);

    /**
     * Importa tenistas desde un CSV de manera asíncrona.
     *
     * @param filePath Ruta al archivo CSV
     * @return CompletableFuture que se completa cuando la importación termina
     */
    CompletableFuture<Void> importFromCsvAsync(String filePath);

    /**
     * Exporta los tenistas actuales a un archivo JSON de manera asíncrona.
     *
     * @param filePath Ruta destino del JSON
     * @return CompletableFuture que se completa cuando la exportación termina
     */
    CompletableFuture<Void> exportToJsonAsync(String filePath);
}
