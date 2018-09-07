package com.demo.justapp.exchanger.di.application

import android.support.annotation.NonNull
import com.demo.justapp.exchanger.data.network.LoggingInterceptor
import com.demo.justapp.exchanger.data.network.RestApi
import com.demo.justapp.exchanger.rx.RxSchedulers
import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Сетевой модуль [dagger.Module] приложения
 *
 * @author Sergey Rodionov
 */
@Module
class NetModule {

    companion object {
        private const val BASE_URL = "https://revolut.duckdns.org/"
        private const val TIMEOUT = 20L
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@NonNull client: OkHttpClient,
                        @NonNull gson: Gson,
                        @NonNull rxSchedulers: RxSchedulers): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(rxSchedulers.getIOScheduler()))
                .build()
    }

    @Singleton
    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .registerTypeAdapter(Date::class.java, DateSerializer())
                .create()
    }

    @Singleton
    @Provides
    fun provideRestApi(@NonNull retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    private class DateSerializer : JsonSerializer<Date> {
        override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?) =
                JsonPrimitive(src?.time?.div(1000))
    }

    private class DateDeserializer : JsonDeserializer<Date> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?) =
                Date(json?.asLong?.times(1000) ?: 0L)
    }

}