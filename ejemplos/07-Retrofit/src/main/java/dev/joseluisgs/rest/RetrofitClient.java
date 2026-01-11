package dev.joseluisgs.rest;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/*

Esto sería lo normal, pero me han cambiado la API y ahor hay que usar un header, y no me apetece hacer una api entera
https://reqres.in/


public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Para usar RxJava
                    .build();
        }
        return retrofit;
    }
}*/

public class RetrofitClient {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;


    // El interceptor para añadir el header a todas las peticiones
    private static void initClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request originalRequest = chain.request();
                            Request newRequest = originalRequest.newBuilder()
                                    .header("x-api-key", "reqres-free-v1")
                                    .build();
                            return chain.proceed(newRequest);
                        }
                    })
                    .build();
        }
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            initClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
