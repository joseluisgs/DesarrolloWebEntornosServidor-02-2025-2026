package dev.joseluisgs.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.joseluisgs.config.Config;
import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.validator.TenistaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Implementación del servicio asíncrono de tenistas que encapsula lógica de negocio,
 * validaciones y uso de caché, coordinando repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @author joseluisgs
 * @see dev.joseluisgs.service.TenistasServiceAsync
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.validator.TenistaValidator
 * @see dev.joseluisgs.config.Config
 */
public class TenistasServiceAsyncImpl implements TenistasServiceAsync {

    private final Logger logger = LoggerFactory.getLogger(TenistasServiceAsyncImpl.class);
    private final TenistasRepository repository;
    private final TenistasStorage storage;
    private final Cache<Long, Tenista> tenistaCache;

    /**
     * Crea el servicio inyectando repositorio y almacenamiento.
     *
     * @param repository Repositorio de tenistas
     * @param storage    Servicio de almacenamiento (CSV/JSON)
     */
    public TenistasServiceAsyncImpl(TenistasRepository repository, TenistasStorage storage) {
        this.storage = storage;
        this.repository = repository;
        logger.info("Inicializando TenistasServiceAsyncImpl con TenistasRepository y configuración de caché");

        // Configuramos la caché con Caffeine: tamaño máximo y tiempo de expiración
        this.tenistaCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(Config.getInstance().getCacheSize())
                .build();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Se obtiene la lista completa de tenistas de manera asíncrona.
     */
    @Override
    public CompletableFuture<List<Tenista>> findAllAsync() {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("Buscando todos los tenistas en el servicio");
            // Llamamos al repositorio para obtener todos los tenistas
            return repository.findAll();
        });
    }

    /**
     * {@inheritDoc}
     * <p>
     * Se intenta recuperar el tenista de la caché primero para optimizar rendimiento.
     * Si no está en caché, se consulta el repositorio.
     * Devuelve un CompletableFuture que puede completar excepcionalmente con
     * TenistaException.NotFoundException si el tenista no existe.
     */
    @Override
    public CompletableFuture<Tenista> findByIdAsync(long id) {
        try {
            // Primero buscamos en caché
            Tenista cached = tenistaCache.getIfPresent(id);
            if (cached != null) {
                logger.info("Tenista con id {} encontrado en caché", id);
                return CompletableFuture.completedFuture(cached);
            }

            // Si no está en caché, buscamos en el repositorio
            return repository.findById(id)
                    .map(CompletableFuture::completedFuture)
                    .orElseGet(() -> CompletableFuture.failedFuture(
                            new TenistaException.NotFoundException("Tenista con ID " + id + " no encontrado.")
                    ));
        } catch (Exception e) {
            // Si ocurre cualquier otra excepción, completamos excepcionalmente
            return CompletableFuture.failedFuture(e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Valida el tenista antes de guardarlo.
     * En caso de error de validación, el CompletableFuture se completa excepcionalmente.
     * Actualiza la caché con el tenista guardado.
     */
    @Override
    public CompletableFuture<Tenista> saveAsync(Tenista tenista) {
        try {
            // Validamos el tenista antes de guardar
            TenistaValidator.validate(tenista);

            // Guardamos en el repositorio
            Tenista saved = repository.save(tenista);

            // Actualizamos la caché con el nuevo tenista
            tenistaCache.put(saved.getId(), saved);

            logger.info("Tenista guardado: {}", saved);
            return CompletableFuture.completedFuture(saved);
        } catch (TenistaException.ValidationException e) {
            // Devolvemos la excepción de validación de forma asíncrona
            return CompletableFuture.failedFuture(e);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Valida el tenista antes de actualizarlo.
     * Invalidamos la caché antes de actualizar.
     * En caso de error de validación o si no existe el tenista, el CompletableFuture se completa excepcionalmente.
     */
    @Override
    public CompletableFuture<Tenista> updateAsync(Tenista tenista) {
        try {
            // Validamos el tenista antes de actualizar
            TenistaValidator.validate(tenista);

            // Invalidamos la caché antes de actualizar
            tenistaCache.invalidate(tenista.getId());

            // Actualizamos el tenista en el repositorio
            Tenista updated = repository.update(tenista)
                    .orElseThrow(() -> new TenistaException.NotFoundException(
                            "Tenista con ID " + tenista.getId() + " no encontrado para actualizar."
                    ));

            // Guardamos la versión actualizada en caché
            tenistaCache.put(updated.getId(), updated);
            logger.info("Tenista actualizado: {}", updated);

            return CompletableFuture.completedFuture(updated);
        } catch (TenistaException.ValidationException | TenistaException.NotFoundException e) {
            // Devolvemos la excepción original asíncronamente
            return CompletableFuture.failedFuture(e);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Borra un tenista por su ID, invalidando la caché.
     * El CompletableFuture se completa excepcionalmente si el tenista no existe.
     */
    @Override
    public CompletableFuture<Boolean> deleteByIdAsync(long id) {
        try {
            // Invalidamos la caché antes de borrar
            tenistaCache.invalidate(id);

            // Intentamos borrar en el repositorio
            boolean deleted = repository.deleteById(id);
            if (!deleted) {
                // Si no se borró, devolvemos excepción
                return CompletableFuture.failedFuture(
                        new TenistaException.NotFoundException("Tenista con ID " + id + " no encontrado para borrar.")
                );
            }

            logger.info("Tenista borrado con id: {}", id);
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Importa tenistas desde un CSV de manera asíncrona.
     * Cada tenista se valida y guarda individualmente de manera paralela.
     * Los errores de validación se registran pero no bloquean la importación de otros tenistas.
     */
    @Override
    public CompletableFuture<Void> importFromCsvAsync(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Leemos los datos desde el CSV usando el storage
                return storage.loadData(filePath);
            } catch (TenistaException.StorageException e) {
                // Si hay error de almacenamiento, completamos el future con excepción
                throw new RuntimeException(e);
            }
        }).thenCompose(tenistas -> {
            // Guardamos cada tenista en paralelo
            List<CompletableFuture<Void>> futures = tenistas.stream()
                    .map(tenista -> CompletableFuture.runAsync(() -> {
                        try {
                            // Validamos y guardamos en repositorio
                            TenistaValidator.validate(tenista);
                            Tenista saved = repository.save(tenista);

                            // Actualizamos la caché con cada tenista
                            tenistaCache.put(saved.getId(), saved);
                        } catch (TenistaException.ValidationException e) {
                            // Logueamos errores de validación, pero no detenemos la importación
                            logger.error("Tenista inválido durante importación: {}", tenista, e);
                        }
                    })).toList();

            // Esperamos a que todas las tareas de guardado terminen
            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        });
    }

    /**
     * {@inheritDoc}
     * <p>
     * Exporta todos los tenistas a un archivo JSON de manera asíncrona.
     * El CompletableFuture se completa excepcionalmente si hay problemas de almacenamiento.
     */
    @Override
    public CompletableFuture<Void> exportToJsonAsync(String filePath) {
        return CompletableFuture.runAsync(() -> {
            try {
                // Obtenemos todos los tenistas del repositorio
                List<Tenista> tenistas = repository.findAll();

                // Guardamos la lista en JSON usando el storage
                storage.saveData(tenistas, filePath);

                logger.info("Datos exportados a JSON: {}", filePath);
            } catch (TenistaException.StorageException e) {
                // Si hay error de escritura, completamos excepcionalmente
                throw new RuntimeException(e);
            }
        });
    }
}
