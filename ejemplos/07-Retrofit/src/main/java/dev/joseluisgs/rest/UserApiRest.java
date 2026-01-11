package dev.joseluisgs.rest;

import dev.joseluisgs.rest.responses.createupdatedelete.Request;
import dev.joseluisgs.rest.responses.createupdatedelete.Response;
import dev.joseluisgs.rest.responses.getall.ResponseGetAll;
import dev.joseluisgs.rest.responses.getbyid.ResponseGetById;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.concurrent.CompletableFuture;

public interface UserApiRest {

    String API_USERS_URL = "https://reqres.in/api/";

    // Ejemplos de llamadas
    // Síncrona
    @GET("users")
    Call<ResponseGetAll> getAllSync();

    // Asíncrona con CompletableFuture
    @GET("users")
    CompletableFuture<ResponseGetAll> getAllCompletableFuture();

    // Con RxJava
    @GET("users")
    Single<ResponseGetAll> getAllWithRxJava();

    // Síncrona con paginación
    @GET("users")
    Call<ResponseGetAll> getAllWithPageSync(@Query("page") int page);

    // Asíncrona con paginación
    @GET("users")
    CompletableFuture<ResponseGetAll> getAllWithPage(@Query("page") int page);

    // Con RxJava y paginación
    @GET("users")
    Single<ResponseGetAll> getAllWithPageRxJava(@Query("page") int page);

    // Síncrona por id
    @GET("users/{id}")
    Call<ResponseGetById> getByIdSync(@Path("id") int id);

    // Asíncrona por id
    @GET("users/{id}")
    CompletableFuture<ResponseGetById> getById(@Path("id") int id);

    // Con RxJava por id
    @GET("users/{id}")
    Single<ResponseGetById> getByIdWithRxJava(@Path("id") int id);

    // Crear un usuario sincrónico
    @POST("users")
    Call<Response> createUserSync(@Body Request user);

    // Crear un usuario asíncrono
    @POST("users")
    CompletableFuture<Response> createUser(@Body Request user);

    // Crear un usuario con RxJava
    @POST("users")
    Single<Response> createUserWithRxJava(@Body Request user);

    // Actualizar un usuario S
    @PUT("users/{id}")
    Call<Response> updateUserSync(@Path("id") int id, @Body Request user);

    // Actualizar un usuario Asíncrono
    @PUT("users/{id}")
    CompletableFuture<Response> updateUser(@Path("id") int id, @Body Request user);

    // Actualizar un usuario con RxJava
    @PUT("users/{id}")
    Single<Response> updateUserWithRxJava(@Path("id") int id, @Body Request user);

    // Eliminar un usuario sincrónico
    @DELETE("users/{id}")
    Call<Response> deleteUserSync(@Path("id") int id);

    // Eliminar un usuario asíncrono
    @DELETE("users/{id}")
    CompletableFuture<Response> deleteUser(@Path("id") int id);

    // Eliminar un usuario con RxJava
    @DELETE("users/{id}")
    Completable deleteUserWithRxJava(@Path("id") int id);

    // Crear un usuario sincrónico con token
    @POST("users")
    Call<Response> createUserWithTokenSync(
            @Header("Authorization") String token,
            @Body Request user
    );

    // Crear un usuario asíncrono con token
    @POST("users")
    CompletableFuture<Response> createUserWithToken(
            @Header("Authorization") String token,
            @Body Request user
    );

    // Crear un usuario con RxJava y token
    @POST("users")
    Single<Response> createUserWithTokenWithRxJava(
            @Header("Authorization") String token,
            @Body Request user
    );
}