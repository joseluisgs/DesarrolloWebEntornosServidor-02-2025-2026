package dev.joseluisgs.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.joseluisgs.config.Config;
import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.notifications.Notification;
import dev.joseluisgs.notifications.NotificationService;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.storage.TenistasStorage;
import dev.joseluisgs.validator.TenistaValidator;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementación del servicio de tenistas reactivo que encapsula lógica de negocio,
 * validaciones y uso de caché, coordinando repositorio y almacenamiento externo.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see TenistasReactiveService
 * @see dev.joseluisgs.repository.TenistasRepository
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.validator.TenistaValidator
 * @see dev.joseluisgs.config.Config
 */
public class TenistasReactiveServiceImpl implements TenistasReactiveService {
    private final Logger logger = LoggerFactory.getLogger(TenistasReactiveServiceImpl.class);
    private final TenistasRepository repository;
    private final TenistasStorage storage;
    private final NotificationService<Tenista> notificaciones;
    private final Cache<Long, Tenista> tenistaCache;

    /**
     * Crea el servicio inyectando repositorio y almacenamiento.
     *
     * @param repository Repositorio de tenistas
     * @param storage    Servicio de almacenamiento (CSV/JSON)
     */
    public TenistasReactiveServiceImpl(TenistasRepository repository, TenistasStorage storage, NotificationService<Tenista> notificaciones) {
        this.storage = storage;
        this.repository = repository;
        this.notificaciones = notificaciones;
        logger.info("Inicializando TenistasReactiveServiceImpl con TenistasRepository y configuración de caché");

        this.tenistaCache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(Config.getInstance().getCacheSize())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    /*@Override
    public Observable<List<Tenista>> findAll() {
        // Un observable es adecuado para flujos que pueden emitir múltiples valores o ninguno.
        // Observable.fromCallable: crea un Observable que ejecuta la operación síncrona dentro de la lambda.
        // Solo se ejecutará cuando alguien se suscriba.
        return Observable.fromCallable(() -> {
            logger.info("Buscando todos los tenistas en el servicio de forma reactiva");
            return repository.findAll();
        }).subscribeOn(Schedulers.io()); // subscribeOn: indica que esta operación debe ejecutarse en un hilo de E/S.
    }
*/
    @Override
    public Observable<Tenista> findAll() {
        // Vamos a simular que en vez de emitir la lista del tiron, emitimos cada tenista uno a uno en un flujo
        // para poder suscribirnos y procesarlos individualmente.
        // Observable que emite cada tenista uno a uno
        return Observable.fromCallable(() -> {
                    // Se obtiene toda la lista de forma síncrona del repositorio
                    List<Tenista> tenistas = repository.findAll();
                    logger.info("Se recuperaron {} tenistas del repositorio", tenistas.size());
                    return tenistas;
                })
                .flatMapIterable(list -> list) // Convierte la lista en un flujo que emite cada tenista individualmente
                .subscribeOn(Schedulers.io()); // Se ejecuta en hilo de I/O para no bloquear el principal
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Tenista> findById(long id) {
        // Single.fromCallable: similar a Observable.fromCallable, pero para un solo resultado.
        // Solo se ejecutará cuando alguien se suscriba.
        return Single.fromCallable(() -> {
                    logger.info("Buscando tenista con id: {} en caché", id);
                    Tenista cached = tenistaCache.getIfPresent(id);
                    if (cached != null) {
                        logger.info("Tenista con id {} encontrado en caché", id);
                        return cached;
                    }

                    // Si el repositorio no lo encuentra, se emite un error en el Single.
                    return repository.findById(id)
                            .orElseThrow(() -> {
                                logger.error("Tenista con id {} no encontrado", id);
                                return new TenistaException.NotFoundException("Tenista con ID " + id + " no encontrado.");
                            });
                })
                .doOnSuccess(tenista -> tenistaCache.put(tenista.getId(), tenista)) // doOnSuccess: ejecuta una acción si la operación es exitosa, sin modificar el flujo.
                .subscribeOn(Schedulers.io());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Tenista> save(Tenista tenista) {
        return Single.fromCallable(() -> {
                    logger.info("Guardando tenista en el servicio");

                    try {
                        // Si la validación falla, lanza una excepción que se convierte en un evento de error del Single.
                        TenistaValidator.validate(tenista);
                    } catch (TenistaException.ValidationException e) {
                        throw e; // La excepción se propaga como un evento onError del Single.
                    }

                    Tenista saved = repository.save(tenista);
                    // 🚀 Enviamos la notificación de tipo CREADO
                    notificaciones.notify(new Notification<>(Notification.Tipo.CREADO, saved));
                    return saved;
                })
                .doOnSuccess(saved -> tenistaCache.put(saved.getId(), saved))
                .subscribeOn(Schedulers.io());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Tenista> update(Tenista tenista) {
        return Single.fromCallable(() -> {
                    logger.info("Actualizando tenista con id: {} en el servicio", tenista.getId());

                    try {
                        TenistaValidator.validate(tenista);
                    } catch (TenistaException.ValidationException e) {
                        throw e;
                    }

                    tenistaCache.invalidate(tenista.getId());

                    Tenista updated = repository.update(tenista)
                            .orElseThrow(() -> {
                                logger.error("Tenista con id {} no encontrado para actualizar", tenista.getId());
                                return new TenistaException.NotFoundException("Tenista con ID " + tenista.getId() + " no encontrado para actualizar.");
                            });
                    // 🚀 Enviamos la notificación de tipo ACTUALIZADO
                    notificaciones.notify(new Notification<>(Notification.Tipo.ACTUALIZADO, updated));
                    return updated;
                })
                .doOnSuccess(updated -> tenistaCache.put(updated.getId(), updated))
                .subscribeOn(Schedulers.io());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<Boolean> deleteById(long id) {
        return Single.fromCallable(() -> {
            logger.info("Borrando tenista con id: {} en el servicio", id);
            tenistaCache.invalidate(id);

            boolean deleted = repository.deleteById(id);
            if (!deleted) {
                logger.error("Tenista con id {} no encontrado para borrar", id);
                throw new TenistaException.NotFoundException("Tenista con ID " + id + " no encontrado para borrar.");
            }
            // 🚀 Enviamos la notificación de tipo BORRADO
            notificaciones.notify(new Notification<>(Notification.Tipo.BORRADO, null));
            return true;
        }).subscribeOn(Schedulers.io());
    }

    /**
     * {@inheritDoc}
     */
    /*@Override
    public Observable<Tenista> importFromCsv(String filePath) {
        // En un flujo reactivo, es más idiomático emitir los elementos uno por uno.
        return Observable.fromCallable(() -> {
                    logger.info("Cargando datos desde archivo: {}", filePath);
                    return storage.loadData(filePath);
                })
                .flatMapIterable(tenistas -> tenistas) // flatMapIterable: transforma la lista completa en un flujo que emite cada tenista individualmente.
                .doOnNext(tenista -> { // doOnNext: ejecuta una acción por cada elemento emitido, sin modificar el flujo.
                    // Validar y guardar en el repositorio
                    try {
                        TenistaValidator.validate(tenista);
                        Tenista savedTenista = repository.save(tenista);
                        // 💡 Aquí, después de que se ha guardado correctamente,
                        // notificamos a los suscriptores.
                        // notificaciones.notify(new Notification<>(Notification.Tipo.CREADO, savedTenista)); // Si quieres notificar por cada tenista creado, descomenta esta línea.
                    } catch (TenistaException.ValidationException e) {
                        logger.error("Error de validación durante la importación: {}", e.getMessage());
                        throw e; // Lanza la excepción para que el Observable se detenga y emita un error.
                    }
                })
                .subscribeOn(Schedulers.io());
    }*/

    // Esta forma es la correcta si queremos que haga el flujo de notificaciones siempre en el save

   /* @Override
    public Observable<Tenista> importFromCsv(String filename) {
        // 1. Single.fromCallable() se usa para operaciones que devuelven un único resultado (la lista de tenistas).
        return Single.fromCallable(() -> {
                    logger.info("Cargando datos desde archivo: {}", filename);
                    return storage.loadData(filename);
                })
                // 2. flatMapObservable: Es el operador que transforma el Single<List<Tenista>> en un Observable<Tenista>.
                // La lambda convierte la lista en un flujo que emite cada tenista individualmente.
                .flatMapObservable(tenistas -> Observable.fromIterable(tenistas))
                // 3. flatMapSingle: Para cada tenista en el flujo, lo guardamos y validamos.
                // El método save() devuelve un Single, por lo que flatMapSingle es el operador correcto.
                .flatMapSingle(tenista -> {
                    TenistaValidator.validate(tenista);
                    return save(tenista); // Usamos el método save del servicio que ya maneja notificaciones y caché.
                })
                .subscribeOn(Schedulers.io()); // Se ejecuta todo el flujo en un hilo de E/S.
    }*/

    // Con el nuevo repositorio
    @Override
    public Observable<Tenista> importFromCsv(String filePath) {
        logger.info("Iniciando importación desde archivo: {}", filePath);

        return storage.loadDataObservable(filePath) // 🔹 ya es Observable<Tenista>
                .flatMap(tenista -> {
                    try {
                        // 1. Validar
                        TenistaValidator.validate(tenista);

                        // 2. Guardar en repositorio
                        Tenista saved = repository.save(tenista);

                        // 3. Notificar (opcional)
                        // notificaciones.notify(new Notification<>(Notification.Tipo.CREADO, saved));

                        // 4. Emitir al flujo el tenista guardado
                        return Observable.just(saved);
                    } catch (TenistaException.ValidationException e) {
                        logger.error("Error de validación: {}", e.getMessage());
                        // Emitimos error para cortar el flujo
                        return Observable.error(e);
                    }
                })
                .doOnNext(t -> logger.debug("Tenista procesado y guardado: {}", t))
                .doOnComplete(() -> logger.info("Importación completada desde {}", filePath))
                .doOnError(e -> logger.error("Error durante la importación desde {}: {}", filePath, e.getMessage()))
                .subscribeOn(Schedulers.io());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Single<List<Tenista>> exportToJson(String filePath) {
        return Single.fromCallable(() -> {
            List<Tenista> tenistas = repository.findAll();
            int savedCount = storage.saveData(tenistas, filePath);
            logger.info("Datos guardados desde el repositorio en el archivo: {} con {} tenistas", filePath, savedCount);
            return tenistas; // Esto sobre, que no devuelva nada o que devuleva size...
        }).subscribeOn(Schedulers.io());
    }
}