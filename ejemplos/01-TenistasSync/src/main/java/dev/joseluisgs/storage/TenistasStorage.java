package dev.joseluisgs.storage;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;

import java.util.List;

/**
 * Abstracción para cargar y guardar datos de tenistas desde/hacia distintos formatos.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.storage.TenistasStorageImpl
 * @see dev.joseluisgs.models.Tenista
 */
public interface TenistasStorage {
    /**
     * Carga tenistas desde un archivo CSV.
     *
     * @param filePath Ruta del CSV
     * @return Lista de tenistas cargados
     * @throws TenistaException.StorageException si hay errores de validación o E/S
     */
    List<Tenista> loadData(String filePath) throws TenistaException.StorageException;

    /**
     * Guarda tenistas en un archivo JSON.
     *
     * @param tenistas Lista de tenistas a guardar
     * @param filePath Ruta del JSON
     * @return Número de registros escritos
     * @throws TenistaException.StorageException si hay errores de escritura
     */
    int saveData(List<Tenista> tenistas, String filePath) throws TenistaException.StorageException;
}