package dev.joseluisgs.service;

import dev.joseluisgs.models.Tenista;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

/**
 * Servicio de dominio para gestionar operaciones de Tenista, incluyendo caché,
 * validaciones y coordinación con repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see TenistasReactiveServiceImpl
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 */
public interface TenistasReactiveService {
    /**
     * Obtiene todos los tenistas de forma reactiva.
     *
     * @return Observable que emite la lista completa de tenistas.
     */
    //Observable<List<Tenista>> findAll();  // Un observable es adecuado para flujos que pueden emitir múltiples valores o ninguno.
    Observable<Tenista> findAll(); // Un observable es adecuado para flujos que pueden emitir múltiples valores o ninguno.

    /**
     * Obtiene un tenista por id de forma reactiva, consultando la caché si es posible.
     *
     * @param id Identificador
     * @return Single que emite el tenista encontrado o un error si no existe.
     */
    Single<Tenista> findById(long id);

    /**
     * Valida y guarda un nuevo tenista de forma reactiva.
     *
     * @param tenista Tenista a guardar
     * @return Single que emite el tenista guardado o un error si los datos no son válidos.
     */
    Single<Tenista> save(Tenista tenista);

    /**
     * Valida y actualiza un tenista existente de forma reactiva.
     *
     * @param tenista Tenista con cambios
     * @return Single que emite el tenista actualizado o un error si no existe o los datos no son válidos.
     */
    Single<Tenista> update(Tenista tenista);

    /**
     * Elimina un tenista por su id de forma reactiva.
     *
     * @param id Identificador
     * @return Single que emite true si se eliminó o un error si no existe.
     */
    Single<Boolean> deleteById(long id);

    /**
     * Importa tenistas desde un CSV de forma reactiva.
     *
     * @param filePath Ruta al archivo CSV
     * @return Observable que emite los tenistas a medida que son procesados, o un error si hay problemas.
     */
    Observable<Tenista> importFromCsv(String filePath);

    /**
     * Exporta los tenistas actuales a un archivo JSON de forma reactiva.
     *
     * @param filePath Ruta destino del JSON
     * @return Single que emite la lista de tenistas guardada, o un error si hay problemas de escritura.
     */
    Single<List<Tenista>> exportToJson(String filePath);
}