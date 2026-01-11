package dev.joseluisgs.services;

import dev.joseluisgs.errors.UserError;
import dev.joseluisgs.model.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserService {

    // Métodos síncronos: usan Either para un manejo de errores explícito y funcional.
    Either<UserError, List<User>> getAllSync();

    Either<UserError, List<User>> getAllWithPageSync(int page);

    Either<UserError, User> getByIdSync(int id);

    Either<UserError, User> createUserSync(User user);

    Either<UserError, User> createUserWithTokenSync(String token, User user);

    Either<UserError, User> updateUserSync(int id, User user);

    Either<UserError, Void> deleteUserSync(int id);

    // Métodos con CompletableFuture: devuelven el resultado o una excepción
    // La gestión de errores se realiza en la cadena de métodos asíncronos
    CompletableFuture<List<User>> getAllCompletableFuture();

    CompletableFuture<List<User>> getAllWithPageCompletableFuture(int page);

    CompletableFuture<User> getByIdCompletableFuture(int id);

    CompletableFuture<User> createUserCompletableFuture(User user);

    CompletableFuture<User> createUserWithTokenCompletableFuture(String token, User user);

    CompletableFuture<User> updateUserCompletableFuture(int id, User user);

    CompletableFuture<Boolean> deleteUserCompletableFuture(int id);

    // Métodos con RxJava: devuelven el resultado o una señal de error
    // La gestión de errores se realiza en la cadena de métodos reactivos
    Single<List<User>> getAllWithRxJava();

    Single<List<User>> getAllWithPageRxJava(int page);

    Single<User> getByIdWithRxJava(int id);

    Single<User> createUserWithRxJava(User user);

    Single<User> createUserWithTokenWithRxJava(String token, User user);

    Single<User> updateUserWithRxJava(int id, User user);

    Completable deleteUserWithRxJava(int id);
}