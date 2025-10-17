package dev.joseluisgs.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.joseluisgs.config.Config;
import dev.joseluisgs.errors.TenistaError;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.validator.TenistaValidator;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class TenistasRailwayServiceImpl implements TenistasRailwayService {
    private final Logger logger = LoggerFactory.getLogger(TenistasRailwayServiceImpl.class);
    private final TenistasRepository repository;
    private final TenistasStorage storage;
    private final Cache<Long, Tenista> tenistaCache;

    /**
     * Crea el servicio inyectando repositorio y almacenamiento.
     *
     * @param repository Repositorio de tenistas
     * @param storage    Servicio de almacenamiento (CSV/JSON)
     */
    public TenistasRailwayServiceImpl(TenistasRepository repository, TenistasStorage storage) {
        this.storage = storage;
        this.repository = repository;
        logger.info("Inicializando TenistasRailwayServiceImpl con TenistasRepository y configuración de caché");

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
    public Either<TenistaError.NotFound, Tenista> findById(long id) {
        logger.info("Buscando tenista con id: {} en caché", id);
        Tenista cached = tenistaCache.getIfPresent(id);
        if (cached != null) {
            logger.info("Tenista con id {} encontrado en caché", id);
            return Either.right(cached);
        }

        Optional<Tenista> tenistaOpt = repository.findById(id);

        if (tenistaOpt.isEmpty()) {
            logger.error("Tenista con id {} no encontrado", id);
            return Either.left(new TenistaError.NotFound("Tenista con ID " + id + " no encontrado."));
        }

        Tenista tenista = tenistaOpt.get();
        tenistaCache.put(tenista.getId(), tenista);
        return Either.right(tenista);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Either<TenistaError.ValidationError, Tenista> save(Tenista tenista) {
        logger.info("Guardando tenista en el servicio");

        try {
            // Validar antes de guardar
            TenistaValidator.validate(tenista);
            Tenista saved = repository.save(tenista);
            tenistaCache.put(saved.getId(), saved);
            return Either.right(saved);
        } catch (Exception e) { // Se asume que el validador lanza una excepción
            logger.error("Error de validación al guardar tenista: {}", e.getMessage());
            return Either.left(new TenistaError.ValidationError("Error de validación: " + e.getMessage()));
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Either<TenistaError, Tenista> update(Tenista tenista) {
        logger.info("Actualizando tenista con id: {} en el servicio", tenista.getId());

        try {
            // Validar antes de actualizar
            TenistaValidator.validate(tenista);
        } catch (Exception e) {
            logger.error("Error de validación al actualizar tenista: {}", e.getMessage());
            return Either.left(new TenistaError.ValidationError("Error de validación: " + e.getMessage()));
        }

        tenistaCache.invalidate(tenista.getId());

        Optional<Tenista> updatedOpt = repository.update(tenista);

        if (updatedOpt.isEmpty()) {
            logger.error("Tenista con id {} no encontrado para actualizar", tenista.getId());
            return Either.left(new TenistaError.NotFound("Tenista con ID " + tenista.getId() + " no encontrado para actualizar."));
        }

        Tenista updated = updatedOpt.get();
        tenistaCache.put(updated.getId(), updated);
        return Either.right(updated);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Either<TenistaError.NotFound, Boolean> deleteById(long id) {
        logger.info("Borrando tenista con id: {} en el servicio", id);
        tenistaCache.invalidate(id);

        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            logger.error("Tenista con id {} no encontrado para borrar", id);
            return Either.left(new TenistaError.NotFound("Tenista con ID " + id + " no encontrado para borrar."));
        }
        return Either.right(true);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Either<TenistaError, Void> importFromCsv(String filePath) {
        logger.info("Cargando datos desde archivo: {}", filePath);
        try {
            List<Tenista> tenistas = storage.loadData(filePath);

            // Validar y guardar todos los tenistas antes de guardarlos
            for (Tenista tenista : tenistas) {
                TenistaValidator.validate(tenista);
                repository.save(tenista);
            }

            logger.info("Datos cargados y guardados en el repositorio desde el archivo: {}. Total: {}", filePath, tenistas.size());
            return Either.right(null); // Retorna nulo para indicar éxito
        } catch (Exception e) { // Se asume que storage.loadData o el validador pueden fallar
            logger.error("Error al importar tenistas: {}", e.getMessage());
            // Si el error es de storage o validación, creamos el tipo de error apropiado
            if (e.getMessage().contains("CSV")) { // Ejemplo de lógica simple de mapeo
                return Either.left(new TenistaError.StorageError("Error de almacenamiento: " + e.getMessage()));
            } else {
                return Either.left(new TenistaError.ValidationError("Error de validación: " + e.getMessage()));
            }
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Either<TenistaError.StorageError, Void> exportToJson(String filePath) {
        try {
            List<Tenista> tenistas = repository.findAll();
            int savedCount = storage.saveData(tenistas, filePath);
            logger.info("Datos guardados desde el repositorio en el archivo: {} con {} tenistas", filePath, savedCount);
            return Either.right(null);
        } catch (Exception e) {
            logger.error("Error al exportar tenistas: {}", e.getMessage());
            return Either.left(new TenistaError.StorageError("Error de almacenamiento: " + e.getMessage()));
        }
    }
}