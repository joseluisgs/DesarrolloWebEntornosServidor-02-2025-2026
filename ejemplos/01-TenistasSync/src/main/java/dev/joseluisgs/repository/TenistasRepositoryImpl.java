package dev.joseluisgs.repository;

import dev.joseluisgs.dao.TenistaEntity;
import dev.joseluisgs.dao.TenistasDao;
import dev.joseluisgs.mappers.TenistaMapper;
import dev.joseluisgs.models.Tenista;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del repositorio de Tenista basada en JDBI y el DAO TenistasDao.
 * Se encarga de mapear entre entidades y modelo de dominio.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.dao.TenistasDao
 * @see dev.joseluisgs.mappers.TenistaMapper
 * @see dev.joseluisgs.models.Tenista
 */
public class TenistasRepositoryImpl implements TenistasRepository {
    private final Logger logger = LoggerFactory.getLogger(TenistasRepositoryImpl.class);
    private final TenistasDao tenistasDao;

    /**
     * Crea el repositorio inyectando el DAO de tenistas.
     *
     * @param tenistasDao DAO utilizado para acceder a la base de datos
     */
    public TenistasRepositoryImpl(TenistasDao tenistasDao) {
        logger.info("Inicializando TenistasRepositoryImpl con TenistasDao");
        this.tenistasDao = tenistasDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tenista> findAll() {
        logger.info("Buscando todos los tenistas en el repositorio");
        return tenistasDao.findAll().stream()
                .map(TenistaMapper::fromEntity)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tenista> findById(long id) {
        logger.info("Buscando tenista con id: {}", id);
        return tenistasDao.findById(id).map(TenistaMapper::fromEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tenista save(Tenista tenista) {
        // Convertimos a entidad con id = -1
        TenistaEntity entityToSave = TenistaMapper.toEntity(tenista);
        entityToSave.setId(Tenista.NEW_TENISTA_ID);

        logger.info("Guardando tenista: {}", entityToSave);

        long newId = tenistasDao.save(entityToSave);

        // Construimos modelo final con el ID generado
        tenista.setId(newId);
        return tenista;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Tenista> update(Tenista tenista) {
        logger.info("Actualizando tenista con id: {}", tenista.getId());
        TenistaEntity entityToUpdate = TenistaMapper.toEntity(tenista);
        int updatedRows = tenistasDao.update(entityToUpdate);
        return updatedRows > 0 ? Optional.of(tenista) : Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(long id) {
        logger.info("Borrando tenista con id: {}", id);
        return tenistasDao.delete(id) > 0;
    }
}