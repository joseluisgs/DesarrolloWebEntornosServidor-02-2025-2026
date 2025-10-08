package dev.joseluisgs.notifications;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificationServiceImpl<T> implements NotificationService<T> {
    private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    // Subject para un flujo de notificaciones "caliente" y con último valor
    // BehaviorSubject guarda y emite el último elemento a los nuevos suscriptores
    private final Subject<Notification<T>> notificacionesSubject;

    public NotificationServiceImpl() {
        logger.info("Inicializando NotificacionesService");
        // El BehaviorSubject necesita un valor inicial, null en este caso.
        notificacionesSubject = BehaviorSubject.create();
    }

    public void notify(Notification<T> notification) {
        logger.info("Notificando a los suscriptores: " + notification);
        notificacionesSubject.onNext(notification); // Emitir la notificación a todos los suscriptores
    }

    public Observable<Notification<T>> getNotificationsSubject() {
        return notificacionesSubject; // Devolver el Subject para que otros puedan suscribirse
    }
}
