package dev.joseluisgs.services;

import dev.joseluisgs.errors.UserError;
import dev.joseluisgs.errors.UserNotFoundError;
import dev.joseluisgs.exceptions.UserNotFoundException;
import dev.joseluisgs.model.User;
import dev.joseluisgs.repository.UserRemoteRepository;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public class UserServiceImpl implements UserService {
    private final UserRemoteRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRemoteRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Métodos síncronos (Sync)
    public Either<UserError, List<User>> getAllSync() {
        try {
            return Either.right(userRepository.getAllSync());
        } catch (Exception e) {
            logger.error("Error al obtener todos los usuarios de forma síncrona", e);
            return Either.left(new UserError("Error al obtener usuarios: " + e.getMessage(), 500));
        }
    }

    public Either<UserError, List<User>> getAllWithPageSync(int page) {
        try {
            return Either.right(userRepository.getAllWithPageSync(page));
        } catch (Exception e) {
            logger.error("Error al obtener la página {} de usuarios de forma síncrona", page, e);
            return Either.left(new UserError("Error al obtener la página " + page + ": " + e.getMessage(), 500));
        }
    }

    public Either<UserError, User> getByIdSync(int id) {
        try {
            return Either.right(userRepository.getByIdSync(id));
        } catch (UserNotFoundException e) {
            logger.error("Usuario no encontrado con id: {}", id, e);
            return Either.left(new UserNotFoundError(id));
        } catch (Exception e) {
            logger.error("Error al obtener el usuario con id {} de forma síncrona", id, e);
            return Either.left(new UserError("Error al obtener el usuario: " + e.getMessage(), 500));
        }
    }

    public Either<UserError, User> createUserSync(User user) {
        try {
            return Either.right(userRepository.createUserSync(user));
        } catch (Exception e) {
            logger.error("Error al crear el usuario de forma síncrona", e);
            return Either.left(new UserError("Error al crear usuario: " + e.getMessage(), 500));
        }
    }

    public Either<UserError, User> createUserWithTokenSync(String token, User user) {
        try {
            return Either.right(userRepository.createUserWithTokenSync(token, user));
        } catch (Exception e) {
            logger.error("Error al crear el usuario con token de forma síncrona", e);
            return Either.left(new UserError("Error al crear usuario con token: " + e.getMessage(), 500));
        }
    }

    public Either<UserError, User> updateUserSync(int id, User user) {
        try {
            return Either.right(userRepository.updateUserSync(id, user));
        } catch (UserNotFoundException e) {
            logger.error("Usuario no encontrado con id: {}", id, e);
            return Either.left(new UserNotFoundError(id));
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario con id {} de forma síncrona", id, e);
            return Either.left(new UserError("Error al actualizar usuario: " + e.getMessage(), 500));
        }
    }

    public Either<UserError, Void> deleteUserSync(int id) {
        try {
            userRepository.deleteUserSync(id);
            return Either.right(null);
        } catch (UserNotFoundException e) {
            logger.error("Usuario no encontrado con id: {}", id, e);
            return Either.left(new UserNotFoundError(id));
        } catch (Exception e) {
            logger.error("Error al eliminar el usuario con id {} de forma síncrona", id, e);
            return Either.left(new UserError("Error al eliminar usuario: " + e.getMessage(), 500));
        }
    }

    // Métodos con CompletableFuture (asíncronos)
    public CompletableFuture<List<User>> getAllCompletableFuture() {
        return userRepository.getAllCompletableFuture();
    }

    public CompletableFuture<List<User>> getAllWithPageCompletableFuture(int page) {
        return userRepository.getAllWithPageCompletableFuture(page);
    }

    public CompletableFuture<User> getByIdCompletableFuture(int id) {
        return userRepository.getByIdCompletableFuture(id);
    }

    public CompletableFuture<User> createUserCompletableFuture(User user) {
        return userRepository.createUserCompletableFuture(user);
    }

    public CompletableFuture<User> createUserWithTokenCompletableFuture(String token, User user) {
        return userRepository.createUserWithTokenCompletableFuture(token, user);
    }

    public CompletableFuture<User> updateUserCompletableFuture(int id, User user) {
        return userRepository.updateUserCompletableFuture(id, user);
    }

    public CompletableFuture<Boolean> deleteUserCompletableFuture(int id) {
        return userRepository.deleteUserCompletableFuture(id);
    }

    // Métodos con RxJava
    public Single<List<User>> getAllWithRxJava() {
        return userRepository.getAllWithRxJava();
    }

    public Single<List<User>> getAllWithPageRxJava(int page) {
        return userRepository.getAllWithPageRxJava(page);
    }

    public Single<User> getByIdWithRxJava(int id) {
        return userRepository.getByIdWithRxJava(id);
    }

    public Single<User> createUserWithRxJava(User user) {
        return userRepository.createUserWithRxJava(user);
    }

    public Single<User> createUserWithTokenWithRxJava(String token, User user) {
        return userRepository.createUserWithTokenWithRxJava(token, user);
    }

    public Single<User> updateUserWithRxJava(int id, User user) {
        return userRepository.updateUserWithRxJava(id, user);
    }

    public Completable deleteUserWithRxJava(int id) {
        return userRepository.deleteUserWithRxJava(id);
    }
}