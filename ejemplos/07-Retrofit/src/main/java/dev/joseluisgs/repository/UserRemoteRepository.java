package dev.joseluisgs.repository;


import dev.joseluisgs.model.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserRemoteRepository {
    // Métodos para obtener todos los usuarios
    List<User> getAllSync();

    CompletableFuture<List<User>> getAllCompletableFuture();

    Single<List<User>> getAllWithRxJava();

    // Métodos para obtener usuarios con paginación
    List<User> getAllWithPageSync(int page);

    CompletableFuture<List<User>> getAllWithPageCompletableFuture(int page);

    Single<List<User>> getAllWithPageRxJava(int page);

    // Métodos para obtener un usuario por ID
    User getByIdSync(int id);

    CompletableFuture<User> getByIdCompletableFuture(int id);

    Single<User> getByIdWithRxJava(int id);

    // Métodos para crear un usuario
    User createUserSync(User user);

    CompletableFuture<User> createUserCompletableFuture(User user);

    Single<User> createUserWithRxJava(User user);

    // Métodos para crear un usuario con token
    User createUserWithTokenSync(String token, User user);

    CompletableFuture<User> createUserWithTokenCompletableFuture(String token, User user);

    Single<User> createUserWithTokenWithRxJava(String token, User user);

    // Métodos para actualizar un usuario
    User updateUserSync(int id, User user);

    CompletableFuture<User> updateUserCompletableFuture(int id, User user);

    Single<User> updateUserWithRxJava(int id, User user);

    // Métodos para eliminar un usuario
    void deleteUserSync(int id);

    CompletableFuture<Boolean> deleteUserCompletableFuture(int id);

    Completable deleteUserWithRxJava(int id);
}