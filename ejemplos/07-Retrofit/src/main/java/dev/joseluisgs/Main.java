import dev.joseluisgs.errors.UserError;
import dev.joseluisgs.model.User;
import dev.joseluisgs.repository.UserRemoteRepository;
import dev.joseluisgs.repository.UserRemoteRepositoryImpl;
import dev.joseluisgs.rest.RetrofitClient;
import dev.joseluisgs.rest.UserApiRest;
import dev.joseluisgs.services.UserService;
import dev.joseluisgs.services.UserServiceImpl;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import retrofit2.Retrofit;

import static java.lang.IO.println;


void main(String[] args) {

    System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8)); // Forzamos UTF-8 en consola


    Retrofit retrofit = RetrofitClient.getClient(UserApiRest.API_USERS_URL);
    UserApiRest api = retrofit.create(UserApiRest.class);
    UserRemoteRepository repository = new UserRemoteRepositoryImpl(api);
    UserService service = new UserServiceImpl(repository);

    // Datos de prueba para crear y actualizar usuarios
    User userToCreate = User.builder()
            .firstName("Jose")
            .lastName("Garcia")
            .job("Developer")
            .email("jose@example.com")
            .avatar("https://reqres.in/img/faces/2-image.jpg")
            .build();

    User userToUpdate = User.builder()
            .firstName("Juan")
            .lastName("Perez")
            .job("Team Lead")
            .email("juan@example.com")
            .build();


    // Token de ejemplo
    String jwtToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    // ----------------------------------------

    // 1. Métodos Síncronos
    println("---- MÉTODOS SÍNCRONOS ----");

    Instant start = Instant.now();

    println("\n-> getAllSync()");
    Either<UserError, List<User>> usersSync = service.getAllSync();
    usersSync
            .peek(users -> users.forEach(System.out::println))
            .peekLeft(error -> System.err.println("Error: " + error.getMessage()));

    println("\n-> getByIdSync(2)");
    Either<UserError, User> userSync = service.getByIdSync(2);
    userSync
            .peek(System.out::println)
            .peekLeft(error -> System.err.println("Error: " + error.getMessage()));

    println("\n-> createUserSync()");
    Either<UserError, User> userCreatedSync = service.createUserSync(userToCreate);
    userCreatedSync
            .peek(System.out::println)
            .peekLeft(error -> System.err.println("Error: " + error.getMessage()));

    println("\n-> createUserWithTokenSync()");
    Either<UserError, User> userCreatedWithTokenSync = service.createUserWithTokenSync(jwtToken, userToCreate);
    userCreatedWithTokenSync
            .peek(System.out::println)
            .peekLeft(error -> System.err.println("Error: " + error.getMessage()));

    println("\n-> updateUserSync(2, userToUpdate)");
    Either<UserError, User> userUpdatedSync = service.updateUserSync(2, userToUpdate);
    userUpdatedSync
            .peek(System.out::println)
            .peekLeft(error -> System.err.println("Error: " + error.getMessage()));

    println("\n-> deleteUserSync(2)");
    Either<UserError, Void> deleteSync = service.deleteUserSync(2);
    deleteSync
            .peek(aVoid -> println("Usuario eliminado correctamente"))
            .peekLeft(error -> System.err.println("Error: " + error.getMessage()));


    Instant end = Instant.now();
    println("\nTiempo total métodos síncronos: " + (end.toEpochMilli() - start.toEpochMilli()) + " ms");


    // ----------------------------------------
    // 2. Métodos Asíncronos con CompletableFuture, al diferencia del síncrono puedo lanzar varias del tiron
    // Me paso la asincronia mal para mantener el flujo al usar el get(), podría usar
    println("\n---- MÉTODOS ASÍNCRONOS CON CompletableFuture ----");

       /*  No bloquees con el get() en un entorno real, es solo para demo, esto bloquea el hilo y para nada es asíncrono
         System.out.println("\n-> getAllCompletableFuture()");
        try {
            service.getAllCompletableFuture().thenAccept(users -> users.forEach(System.out::println)).get();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error: " + e.getMessage());
        }*/

    // ----------------------------------------
    // Lanzamos todas las peticiones asincronas de golpe


    // 1. Lanzar todas las llamadas y guardar los futuros, es asin de esta forma como se consigue la concurrencia
    println("\n-> Lanzando todas las llamadas...");
    start = Instant.now();

    CompletableFuture<List<User>> futureAllUsers = service.getAllCompletableFuture();
    CompletableFuture<User> futureUserById = service.getByIdCompletableFuture(2);
    CompletableFuture<User> futureCreateUser = service.createUserCompletableFuture(userToCreate);
    CompletableFuture<User> futureCreateUserWithToken = service.createUserWithTokenCompletableFuture(jwtToken, userToCreate);

    // 2. Esperar a que todas las llamadas se completen
    // Usamos allOf para esperar a todos los CompletableFuture. Esto es clave para la concurrencia., cada una se habrá hecho a su rirmo
    CompletableFuture<Void> allFutures = CompletableFuture.allOf(
            futureAllUsers,
            futureUserById,
            futureCreateUser,
            futureCreateUserWithToken
    );

    // 3. Bloquear y recoger los resultados, ahora si podemos usar get() porque ya sabemos que han terminado
    try {
        println("\n-> Esperando y recogiendo los resultados...");
        allFutures.get(); // Bloqueamos hasta que todas las peticiones terminen

        // Una vez que sabemos que todas las peticiones han terminado, recogemos sus resultados
        // Sin la necesidad de un thenAccept porque lo podemos hacer de forma secuencial al final.
        println("\n-> Resultado de getAllCompletableFuture()");
        futureAllUsers.thenAccept(users -> users.forEach(System.out::println)).get();


        println("\n-> Resultado de getByIdCompletableFuture(2)");
        futureUserById.thenAccept(System.out::println).get();

        println("\n-> Resultado de createUserCompletableFuture()");
        futureCreateUser.thenAccept(System.out::println).get();

        println("\n-> Resultado de createUserWithTokenCompletableFuture()");
        futureCreateUserWithToken.thenAccept(System.out::println).get();

    } catch (InterruptedException | ExecutionException e) {
        println("Error en alguna de las llamadas: " + e.getMessage());
    }

    // Update, es independiente, no hace falta esperar a las otras
    System.out.println("\n-> updateUserCompletableFuture(2, userToUpdate)");
    CompletableFuture<User> futureUpdateUser = service.updateUserCompletableFuture(2, userToUpdate);
    futureUpdateUser
            .thenAccept(System.out::println)
            .exceptionally(ex -> {
                System.err.println("Error: " + ex.getMessage());
                return null;
            });

    System.out.println("\n-> deleteUserCompletableFuture(2)");
    CompletableFuture<Boolean> futureDeleteUser = service.deleteUserCompletableFuture(2);
    futureDeleteUser
            .thenAccept(deleted -> System.out.println("Usuario eliminado correctamente"))
            .exceptionally(ex -> {
                System.err.println("Error: " + ex.getMessage());
                return null;
            });


    end = Instant.now();
    println("\nTiempo total métodos asíncronos con CompletableFuture: " + (end.toEpochMilli() - start.toEpochMilli()) + " ms");

    // 3. Métodos Reactivos con RxJava (con el nuevo enfoque de zip)
    println("\n---- MÉTODOS REACTIVOS CON RxJava ----");
    println("\n-> Lanzando todas las llamadas reactivas...");

    start = Instant.now();

    Single<List<User>> rxAllUsers = service.getAllWithRxJava();
    Single<User> rxUserById = service.getByIdWithRxJava(2);
    Single<User> rxCreateUser = service.createUserWithRxJava(userToCreate);
    Single<User> rxCreateUserWithToken = service.createUserWithTokenWithRxJava(jwtToken, userToCreate);
    // Usamos zip para esperar a que todas las llamadas se completen
    Single.zip(
                    rxAllUsers,
                    rxUserById,
                    rxCreateUser,
                    rxCreateUserWithToken,
                    (users, userById, createdUser, createdUserWithToken) -> {
                        println("\n-> Resultados de las llamadas RxJava combinadas:");
                        println("-> getAllWithRxJava():");
                        users.forEach(IO::println);
                        println("\n-> getByIdWithRxJava(2):");
                        println(userById);
                        println("\n-> createUserWithRxJava():");
                        println(createdUser);
                        println("\n-> createUserWithTokenWithRxJava():");
                        println(createdUserWithToken);
                        return "Todas las llamadas reactivas completadas.";
                    }
            )
            .subscribeOn(Schedulers.io()) // Ejecutar en el scheduler de IO
            .blockingSubscribe(
                    result -> println("\n" + result),
                    error -> System.err.println("\nError en alguna llamada reactiva: " + error.getMessage())
            );

    // Update, es independiente, no hace falta esperar a las otras
    System.out.println("\n-> updateUserWithRxJava(2, userToUpdate)");
    Single<User> rxUpdateUser = service.updateUserWithRxJava(2, userToUpdate);
    rxUpdateUser
            .blockingSubscribe(
                    user -> System.out.println("Usuario actualizado: " + user),
                    error -> System.err.println("Error: " + error.getMessage())
            );

    System.out.println("\n-> deleteUserWithRxJava(1)");
    Completable rxDeleteUser = service.deleteUserWithRxJava(1);
    rxDeleteUser.blockingSubscribe(
            () -> System.out.println("Usuario eliminado correctamente"),
            error -> System.err.println("Error: " + error.getMessage())
    );


    end = Instant.now();
    println("\nTiempo total métodos con RxJava: " + (end.toEpochMilli() - start.toEpochMilli()) + " ms");

    System.exit(0); // Forzamos la salida para evitar problemas con hilos no daemon
}
