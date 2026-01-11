package dev.joseluisgs.notifications;


import java.time.LocalDateTime;

public class Notification<T> {
    private final Tipo tipo;
    private final T data;
    private final LocalDateTime createdAt;

    public Notification(Tipo tipo, T data) {
        this.tipo = tipo;
        this.data = data;
        this.createdAt = LocalDateTime.now();
    }

    public Tipo getTipo() {
        return tipo;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "tipo=" + tipo +
                ", data=" + data +
                ", createdAt=" + createdAt +
                '}';
    }

    public enum Tipo {CREADO, ACTUALIZADO, BORRADO, ERROR}
}