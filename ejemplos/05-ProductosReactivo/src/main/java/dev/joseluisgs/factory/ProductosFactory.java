package dev.joseluisgs.factory;

import dev.joseluisgs.models.Categoria;
import dev.joseluisgs.models.Producto;
import dev.joseluisgs.notification.NotificationService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.ArrayList;
import java.util.List;

// Documentación de RxJava3
// https://github.com/ReactiveX/RxJava/wiki/Creating-Observables

/**
 * Flujos frios o Cold Flows son aquellos que no emiten nada hasta que alguien se suscribe a ellos.
 * Cada suscriptor recibe su propia secuencia de datos desde el principio.
 * Ejemplos: Observable, Single, Maybe, Flowable.
 */

public class ProductosFactory {
    private static NotificationService notification = null;

    public ProductosFactory(NotificationService notification) {
        this.notification = notification;
    }

    public List<Producto> getProductosLista() {
        var nombres = new String[]{
                "Televisor", "Ordenador", "Camiseta", "Pantalones", "Manzanas", "Plátanos"
        };
        var precios = new double[]{
                500.0, 1000.0, 20.0, 30.0, 2.0, 1.5
        };
        var cantidades = new int[]{
                10, 5, 50, 40, 100, 120
        };


        var productos = new ArrayList<Producto>();

        var max = 50;

        for (int i = 0; i < max; i++) {
            var index = (int) (Math.random() * nombres.length);
            var producto = new Producto(
                    nombres[index],
                    precios[index],
                    cantidades[index],
                    Categoria.values()[index % Categoria.values().length]
            );
            productos.add(producto);
            notification.notify("Sync: Producto " + (i + 1) + " generado " + producto);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.err.println("Error generando productos: " + e.getMessage());
            }
        }
        return productos;
    }

    public Single<List<Producto>> getProductosSingle() {
        // Usamos Single para devolver la lista completa de productos
        return Single.fromCallable(this::getProductosLista);
    }

    public Observable<Producto> getProductosObservable() {
        var nombres = new String[]{
                "Televisor", "Ordenador", "Camiseta", "Pantalones", "Manzanas", "Plátanos"
        };
        var precios = new double[]{
                500.0, 1000.0, 20.0, 30.0, 2.0, 1.5
        };
        var cantidades = new int[]{
                10, 5, 50, 40, 100, 120
        };

        var max = 50;

        // Emitimos los productos uno a uno
        return Observable.create(emitter -> {
            for (int i = 0; i < max; i++) {
                var index = (int) (Math.random() * nombres.length);
                var producto = new Producto(
                        nombres[index],
                        precios[index],
                        cantidades[index],
                        Categoria.values()[index % Categoria.values().length]
                );
                notification.notify("Reactive: Producto " + (i + 1) + " generado " + producto);
                emitter.onNext(producto); // Emitimos el producto
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    emitter.onError(e); // Emitimos el error
                }
            }
            emitter.onComplete(); // Indicamos que hemos terminado
        });

    }


}
