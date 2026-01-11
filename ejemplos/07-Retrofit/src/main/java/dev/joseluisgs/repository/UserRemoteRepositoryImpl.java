package dev.joseluisgs.repository;

import dev.joseluisgs.exceptions.UserNotFoundException;
import dev.joseluisgs.mapper.UserMapper;
import dev.joseluisgs.model.User;
import dev.joseluisgs.rest.UserApiRest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserRemoteRepositoryImpl implements UserRemoteRepository {
    private final UserApiRest userApiRest;
    private final Logger logger = LoggerFactory.getLogger(UserRemoteRepositoryImpl.class);

    public UserRemoteRepositoryImpl(UserApiRest userApiRest) {
        this.userApiRest = userApiRest;
    }

    // Métodos para obtener todos los usuarios
    public List<User> getAllSync() {
        var call = userApiRest.getAllSync();
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error: " + response.code());
            }
            return response.body().getData().stream()
                    .map(UserMapper::toUserFromCreate)
                    .toList();
        } catch (Exception e) {
            logger.error("Error al obtener todos los usuarios de forma síncrona", e);
            throw new RuntimeException("Error al obtener todos los usuarios de forma síncrona", e);
        }
    }

    public CompletableFuture<List<User>> getAllCompletableFuture() {
        return userApiRest.getAllCompletableFuture()
                .thenApply(response -> response.getData().stream()
                        .map(UserMapper::toUserFromCreate)
                        .toList())
                .exceptionally(e -> {
                    logger.error("Error al obtener todos los usuarios con CompletableFuture", e);
                    throw new RuntimeException("Error al obtener todos los usuarios con CompletableFuture", e);
                });
    }

    public Single<List<User>> getAllWithRxJava() {
        return userApiRest.getAllWithRxJava()
                .subscribeOn(Schedulers.io())
                .map(response -> response.getData().stream()
                        .map(UserMapper::toUserFromCreate)
                        .toList())
                .doOnError(e -> logger.error("Error al obtener todos los usuarios con RxJava", e));
    }

    // Métodos para obtener usuarios con paginación
    public List<User> getAllWithPageSync(int page) {
        var call = userApiRest.getAllWithPageSync(page);
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error: " + response.code());
            }
            return response.body().getData().stream()
                    .map(UserMapper::toUserFromCreate)
                    .toList();
        } catch (Exception e) {
            logger.error("Error al obtener la página {} de usuarios de forma síncrona", page, e);
            throw new RuntimeException("Error al obtener la página " + page + " de usuarios de forma síncrona", e);
        }
    }

    public CompletableFuture<List<User>> getAllWithPageCompletableFuture(int page) {
        return userApiRest.getAllWithPage(page)
                .thenApply(response -> response.getData().stream()
                        .map(UserMapper::toUserFromCreate)
                        .toList())
                .exceptionally(e -> {
                    logger.error("Error al obtener la página {} de usuarios con CompletableFuture", page, e);
                    throw new RuntimeException("Error al obtener la página " + page + " de usuarios con CompletableFuture", e);
                });
    }

    public Single<List<User>> getAllWithPageRxJava(int page) {
        return userApiRest.getAllWithPageRxJava(page)
                .subscribeOn(Schedulers.io())
                .map(response -> response.getData().stream()
                        .map(UserMapper::toUserFromCreate)
                        .toList())
                .doOnError(e -> logger.error("Error al obtener la página {} de usuarios con RxJava", page, e));
    }

    // Métodos para obtener un usuario por ID
    public User getByIdSync(int id) {
        var call = userApiRest.getByIdSync(id);
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                if (response.code() == 404) {
                    throw new UserNotFoundException("Usuario no encontrado con id: " + id);
                } else {
                    throw new Exception("Error: " + response.code());
                }
            }
            return UserMapper.toUserFromCreate(response.body().getData());
        } catch (Exception e) {
            logger.error("Error al obtener el usuario con id {} de forma síncrona", id, e);
            throw new RuntimeException("Error al obtener el usuario de forma síncrona", e);
        }
    }

    public CompletableFuture<User> getByIdCompletableFuture(int id) {
        return userApiRest.getById(id)
                .thenApply(response -> UserMapper.toUserFromCreate(response.getData()))
                .exceptionally(e -> {
                    if (e.getCause() instanceof retrofit2.HttpException && ((retrofit2.HttpException) e.getCause()).code() == 404) {
                        throw new UserNotFoundException("Usuario no encontrado con id: " + id);
                    }
                    logger.error("Error al obtener el usuario con id {} con CompletableFuture", id, e);
                    throw new RuntimeException("Error al obtener el usuario con CompletableFuture", e);
                });
    }

    public Single<User> getByIdWithRxJava(int id) {
        return userApiRest.getByIdWithRxJava(id)
                .subscribeOn(Schedulers.io())
                .map(response -> UserMapper.toUserFromCreate(response.getData()))
                .doOnError(e -> {
                    if (e instanceof retrofit2.HttpException && ((retrofit2.HttpException) e).code() == 404) {
                        logger.error("Usuario no encontrado con id: {}", id, e);
                    } else {
                        logger.error("Error al obtener el usuario con id {} con RxJava", id, e);
                    }
                });
    }

    // Métodos para crear un usuario
    public User createUserSync(User user) {
        var call = userApiRest.createUserSync(UserMapper.toRequest(user));
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error: " + response.code());
            }
            return UserMapper.toUserFromCreate(response.body());
        } catch (Exception e) {
            logger.error("Error al crear un usuario de forma síncrona", e);
            throw new RuntimeException("Error al crear un usuario de forma síncrona", e);
        }
    }

    public CompletableFuture<User> createUserCompletableFuture(User user) {
        return userApiRest.createUser(UserMapper.toRequest(user))
                .thenApply(UserMapper::toUserFromCreate)
                .exceptionally(e -> {
                    logger.error("Error al crear un usuario con CompletableFuture", e);
                    throw new RuntimeException("Error al crear un usuario con CompletableFuture", e);
                });
    }

    public Single<User> createUserWithRxJava(User user) {
        return userApiRest.createUserWithRxJava(UserMapper.toRequest(user))
                .subscribeOn(Schedulers.io())
                .map(UserMapper::toUserFromCreate)
                .doOnError(e -> logger.error("Error al crear un usuario con RxJava", e));
    }

    // Métodos para crear un usuario con token
    public User createUserWithTokenSync(String token, User user) {
        var call = userApiRest.createUserWithTokenSync(token, UserMapper.toRequest(user));
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                throw new Exception("Error: " + response.code());
            }
            return UserMapper.toUserFromCreate(response.body());
        } catch (Exception e) {
            logger.error("Error al crear un usuario con token de forma síncrona", e);
            throw new RuntimeException("Error al crear un usuario con token de forma síncrona", e);
        }
    }

    public CompletableFuture<User> createUserWithTokenCompletableFuture(String token, User user) {
        return userApiRest.createUserWithToken(token, UserMapper.toRequest(user))
                .thenApply(UserMapper::toUserFromCreate)
                .exceptionally(e -> {
                    logger.error("Error al crear un usuario con token y CompletableFuture", e);
                    throw new RuntimeException("Error al crear un usuario con token y CompletableFuture", e);
                });
    }

    public Single<User> createUserWithTokenWithRxJava(String token, User user) {
        return userApiRest.createUserWithTokenWithRxJava(token, UserMapper.toRequest(user))
                .subscribeOn(Schedulers.io())
                .map(UserMapper::toUserFromCreate)
                .doOnError(e -> logger.error("Error al crear un usuario con token y RxJava", e));
    }

    // Métodos para actualizar un usuario
    public User updateUserSync(int id, User user) {
        var call = userApiRest.updateUserSync(id, UserMapper.toRequest(user));
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                if (response.code() == 404) {
                    throw new UserNotFoundException("Usuario no encontrado con id: " + id);
                } else {
                    throw new Exception("Error: " + response.code());
                }
            }
            return UserMapper.toUserFromUpdate(response.body(), id);
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario con id {} de forma síncrona", id, e);
            throw new RuntimeException("Error al actualizar el usuario de forma síncrona", e);
        }
    }

    public CompletableFuture<User> updateUserCompletableFuture(int id, User user) {
        return userApiRest.updateUser(id, UserMapper.toRequest(user))
                .thenApply(response -> UserMapper.toUserFromUpdate(response, id))
                .exceptionally(e -> {
                    if (e.getCause() instanceof retrofit2.HttpException && ((retrofit2.HttpException) e.getCause()).code() == 404) {
                        throw new UserNotFoundException("Usuario no encontrado con id: " + id);
                    }
                    logger.error("Error al actualizar el usuario con id {} con CompletableFuture", id, e);
                    throw new RuntimeException("Error al actualizar el usuario con CompletableFuture", e);
                });
    }

    public Single<User> updateUserWithRxJava(int id, User user) {
        return userApiRest.updateUserWithRxJava(id, UserMapper.toRequest(user))
                .subscribeOn(Schedulers.io())
                .map(response -> UserMapper.toUserFromUpdate(response, id))
                .doOnError(e -> {
                    if (e instanceof retrofit2.HttpException && ((retrofit2.HttpException) e).code() == 404) {
                        logger.error("Usuario no encontrado con id: {}", id, e);
                    } else {
                        logger.error("Error al actualizar el usuario con id {} con RxJava", id, e);
                    }
                });
    }

    // Métodos para eliminar un usuario
    public void deleteUserSync(int id) {
        var call = userApiRest.deleteUserSync(id);
        try {
            var response = call.execute();
            if (!response.isSuccessful()) {
                if (response.code() == 404) {
                    throw new UserNotFoundException("Usuario no encontrado con id: " + id);
                } else {
                    throw new Exception("Error: " + response.code());
                }
            }
        } catch (Exception e) {
            logger.error("Error al eliminar el usuario con id {} de forma síncrona", id, e);
            throw new RuntimeException("Error al eliminar el usuario de forma síncrona", e);
        }
    }

    public CompletableFuture<Boolean> deleteUserCompletableFuture(int id) {
        return userApiRest.deleteUser(id)
                .thenApply(response -> {
                    // Devolvemos true si todo ha ido bien
                    return true;
                })
                .exceptionally(e -> {
                    if (e.getCause() instanceof retrofit2.HttpException && ((retrofit2.HttpException) e.getCause()).code() == 404) {
                        throw new UserNotFoundException("Usuario no encontrado con id: " + id);
                    }
                    logger.error("Error al eliminar el usuario con id {} con CompletableFuture", id, e);
                    throw new RuntimeException("Error al eliminar el usuario con CompletableFuture", e);
                });
    }

    public Completable deleteUserWithRxJava(int id) {
        return userApiRest.deleteUserWithRxJava(id)
                .subscribeOn(Schedulers.io())// Convierte Single<Response> a Completable
                .doOnError(e -> {
                    if (e instanceof retrofit2.HttpException && ((retrofit2.HttpException) e).code() == 404) {
                        logger.error("Usuario no encontrado con id: {}", id, e);
                    } else {
                        logger.error("Error al eliminar el usuario con id {} con RxJava", id, e);
                    }
                });
    }
}