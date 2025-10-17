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
import java.util.concurrent.TimeUnit;

/**
 * Implementación del servicio de tenistas que encapsula lógica de negocio,
 * validaciones y uso de caché, coordinando repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.service.TenistasService
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.validator.TenistaValidator
 * @see dev.joseluisgs.config.Config
 */
public class TenistasServiceImpl implements TenistasService {
    private final Logger logger = LoggerFactory.getLogger(TenistasServiceImpl.class);
    private final TenistasRepository repository;
    private final TenistasStorage storage;
    private final Cache<Long, Tenista> tenistaCache;

    /**
     * Crea el servicio inyectando repositorio y almacenamiento.
     *
     * @param repository Repositorio de tenistas
     * @param storage    Servicio de almacenamiento (CSV/JSON)
     */
    public TenistasServiceImpl(TenistasRepository repository, TenistasStorage storage) {
        this.storage = storage;
        this.repository = repository;
        logger.info("Inicializando TenistasServiceImpl con TenistasRepository y configuración de caché");

        this.tenistaCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(Config.getInstance().getCacheSize())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tenista> findAll() {
        logger.info("Buscando todos los tenistas en el servicio");
        return repository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tenista findById(long id) throws TenistaException.NotFoundException {
        logger.info("Buscando tenista con id: {} en caché", id);
        Tenista cached = tenistaCache.getIfPresent(id);
        if (cached != null) {
            logger.info("Tenista con id {} encontrado en caché", id);
            return cached;
        }

        Tenista tenista = repository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Tenista con id {} no encontrado", id);
                    return new TenistaException.NotFoundException("Tenista con ID " + id + " no encontrado.");
                });

        tenistaCache.put(tenista.getId(), tenista);
        return tenista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tenista save(Tenista tenista) throws TenistaException.ValidationException {
        logger.info("Guardando tenista en el servicio");

        // Validar antes de guardar
        TenistaValidator.validate(tenista);

        Tenista saved = repository.save(tenista);
        tenistaCache.put(saved.getId(), saved);
        return saved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tenista update(Tenista tenista) throws TenistaException.NotFoundException, TenistaException.ValidationException {
        logger.info("Actualizando tenista con id: {} en el servicio", tenista.getId());

        // Validar antes de actualizar
        TenistaValidator.validate(tenista);

        tenistaCache.invalidate(tenista.getId());

        Tenista updated = repository.update(tenista)
                .orElseThrow(() -> {
                    logger.error("Tenista con id {} no encontrado para actualizar", tenista.getId());
                    return new TenistaException.NotFoundException("Tenista con ID " + tenista.getId() + " no encontrado para actualizar.");
                });

        tenistaCache.put(updated.getId(), updated);
        return updated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(long id) throws TenistaException.NotFoundException {
        logger.info("Borrando tenista con id: {} en el servicio", id);
        tenistaCache.invalidate(id);

        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            logger.error("Tenista con id {} no encontrado para borrar", id);
            throw new TenistaException.NotFoundException("Tenista con ID " + id + " no encontrado para borrar.");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importFromCsv(String filePath) throws TenistaException.StorageException, TenistaException.ValidationException {
        logger.info("Cargando datos desde archivo: {}", filePath);
        List<Tenista> tenistas = storage.loadData(filePath);

        // Validar todos los tenistas antes de guardarlos
        for (Tenista tenista : tenistas) {
            try {
                TenistaValidator.validate(tenista);
                repository.save(tenista);
            } catch (TenistaException.ValidationException e) {
                logger.error("Error de validación para el tenista {}: {}", tenista, e.getMessage());
                throw e; // Re-lanzar la excepción para que el proceso de importación falle
            }
        }

        logger.info("Datos cargados y guardados en el repositorio desde el archivo: {}. Total: {}", filePath, tenistas.size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportToJson(String filePath) throws TenistaException.StorageException {
        List<Tenista> tenistas = repository.findAll();
        int savedCount = storage.saveData(tenistas, filePath);
        logger.info("Datos guardados desde el repositorio en el archivo: {} con {} tenistas", filePath, savedCount);
    }
}