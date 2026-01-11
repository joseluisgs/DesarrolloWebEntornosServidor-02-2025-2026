package dev.joseluisgs.service;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;

import java.util.List;

/**
 * Servicio de dominio para gestionar operaciones de Tenista, incluyendo caché,
 * validaciones y coordinación con repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.service.TenistasServiceImpl
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 */
public interface TenistasService {
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
     * @return Tenista encontrado
     * @throws TenistaException.NotFoundException si no existe
     */
    Tenista findById(long id) throws TenistaException.NotFoundException;

    /**
     * Valida y guarda un nuevo tenista.
     *
     * @param tenista Tenista a guardar
     * @return Tenista guardado
     * @throws TenistaException.ValidationException si los datos no son válidos
     */
    Tenista save(Tenista tenista) throws TenistaException.ValidationException;

    /**
     * Valida y actualiza un tenista existente.
     *
     * @param tenista Tenista con cambios
     * @return Tenista actualizado
     * @throws TenistaException.NotFoundException   si no existe
     * @throws TenistaException.ValidationException si los datos no son válidos
     */
    Tenista update(Tenista tenista) throws TenistaException.NotFoundException, TenistaException.ValidationException;

    /**
     * Elimina un tenista por su id.
     *
     * @param id Identificador
     * @return true si se eliminó
     * @throws TenistaException.NotFoundException si no existe
     */
    boolean deleteById(long id) throws TenistaException.NotFoundException;

    /**
     * Importa tenistas desde un CSV y los guarda tras validarlos.
     *
     * @param filePath Ruta al archivo CSV
     * @throws TenistaException.StorageException    si hay problemas de E/S
     * @throws TenistaException.ValidationException si los datos son inválidos
     */
    void importFromCsv(String filePath) throws TenistaException.StorageException, TenistaException.ValidationException;

    /**
     * Exporta los tenistas actuales a un archivo JSON.
     *
     * @param filePath Ruta destino del JSON
     * @throws TenistaException.StorageException si hay problemas de escritura
     */
    void exportToJson(String filePath) throws TenistaException.StorageException;
}