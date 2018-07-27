package com.demo.justapp.exchanger.di.application;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.data.network.LoggingInterceptor;
import com.demo.justapp.exchanger.data.network.RestApi;
import com.demo.justapp.exchanger.rx.RxSchedulers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Сетевой модуль {@link Module} приложения
 *
 * @author Sergey Rodionov
 */
@Module
public class NetModule {

    private static final String BASE_URL = "https://revolut.duckdns.org/";
    private static final int TIMEOUT = 20;

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS);

        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@NonNull OkHttpClient client, @NonNull Gson gson, @NonNull RxSchedulers schedulers) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.getIOScheduler()))
                .build();
    }

    @Singleton
    @Provides
    Gson provideGSON() {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class,
                        (JsonDeserializer<Date>) (json, typeOfT, context)
                                -> new Date(json.getAsJsonPrimitive().getAsLong()))
                .create();
    }

    @Singleton
    @Provides
    RestApi provideRestApi(@NonNull Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

}
