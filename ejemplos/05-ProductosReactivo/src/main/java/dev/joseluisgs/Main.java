package dev.joseluisgs;

import dev.joseluisgs.factory.ProductosFactory;
import dev.joseluisgs.models.Categoria;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        // Obtenemos las notificaciones
        var notificationService = new dev.joseluisgs.notification.NotificationService();
            // Y escuchamos las notificaciones
        notificationService.getNotificationsSubject()
                .subscribe(notification -> {
                        System.out.println("🔔 Notificación: " + notification);
                });


        // Hola!


        // Creamos la fábrica de productos
        var productosFactory = new ProductosFactory(notificationService);

        var inicio = Instant.now();
        productosFactory.getProductosLista().stream()
                //.filter(producto -> producto.getCategoria() == Categoria.ELECTRONICA)
                .limit(3)
                .forEach(producto -> System.out.println("Producto: " + producto));
        var fin = Instant.now();
        System.out.println("⏱️ Tiempo total: " + (fin.toEpochMilli() - inicio.toEpochMilli()) + " ms");
        notificationService.notify("Sync: Hemos finalizado el procesamiento de productos!");

        System.out.println();
        System.out.println();
        System.out.println();

        // Ahora vamos asíncrono
       /* var inicio2 = Instant.now();
        // Observable para un flujo frío, tomamos solo 10
        var fin2 = Instant.now();
        System.out.println("🔶 Tiempo total RxJava: " + (fin2.toEpochMilli() - inicio2.toEpochMilli()) + " ms");*/

        // Ahora procesamos de una a una, sobre la marcha, no necesitamos que se produzcan todas para encontrar lo que queremos
        var inicio3 = Instant.now();
        productosFactory.getProductosObservable()
                //.subscribeOn(Schedulers.io()) // Hacemos el procesamiento en otro hilo, opcional
                //.filter(producto -> producto.getCategoria() == Categoria.ELECTRONICA)
                .take(3) // Solo tomamos 3
                .subscribe(
                        // Si recibimos un producto
                        producto -> System.out.println("Producto recibido: " + producto),
                        // Si hay un error
                        error -> System.err.println("Error: " + error.getMessage()),
                        // Si se completa
                        () -> {
                            var fin3 = Instant.now();
                            System.out.println("✅ Completado el flujo de productos electrónicos");
                            System.out.println("⏱️ Tiempo total RxJava con procesamiento: " + (fin3.toEpochMilli() - inicio3.toEpochMilli()) + " ms");
                            notificationService.notify("React: Hemos finalizado el procesamiento de productos!");
                        }
                );

        System.out.println();
        System.out.println();
        System.out.println();


        try {
            Thread.sleep(5000); // Esperamos 5 segundos
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var inicio4 = Instant.now();
        productosFactory.getProductosObservable()
                //.subscribeOn(Schedulers.io()) // Hacemos el procesamiento en otro hilo, opcional
                .filter(producto -> producto.getCategoria() == Categoria.ELECTRONICA)
                .take(3) // Solo tomamos 3
                .toList() // Convertimos a Single<List<Producto>>
                .subscribe(
                        // Si recibimos la lista de productos
                        productos -> {
                            System.out.println("Productos recibidos: ");
                            productos.forEach(producto -> System.out.println(" - " + producto));
                            var fin4 = Instant.now();
                            System.out.println("⏱️ Tiempo total RxJava con procesamiento y lista: " + (fin4.toEpochMilli() - inicio4.toEpochMilli()) + " ms");
                        },
                        // Si hay un error
                        error -> System.err.println("Error: " + error.getMessage())
                );

    }
}