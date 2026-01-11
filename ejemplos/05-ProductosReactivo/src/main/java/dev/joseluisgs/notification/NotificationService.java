package dev.joseluisgs.notification;

import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;


/**
 * Hot Flow o flujo "caliente" es aquel que emite datos independientemente de si hay suscriptores o no.
 * Los suscriptores reciben los datos emitidos a partir del momento en que se suscriben.
 * Ejemplos: Subject, PublishSubject, BehaviorSubject, ReplaySubject.
 */
public class NotificationService {

    // Subject para un flujo de notificaciones "caliente" y con último valor
    // BehaviorSubject guarda y emite el último elemento a los nuevos suscriptores
    private final Subject<String> notificacionesSubject;

    public NotificationService() {
        // El BehaviorSubject necesita un valor inicial, null en este caso.
        notificacionesSubject = BehaviorSubject.create();
    }

    public void notify(String notification) {
        notificacionesSubject.onNext(notification); // Emitir la notificación a todos los suscriptores
    }

    public Subject<String> getNotificationsSubject() {
        return notificacionesSubject; // Devolver el Subject para que otros puedan suscribirse
    }
}
