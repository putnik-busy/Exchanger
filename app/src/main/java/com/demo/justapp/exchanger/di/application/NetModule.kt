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
        private const val BASE_URL: String = "https://revolut.duckdns.org/"
        private const val TIMEOUT: Long = 20
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
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
                        .createWithScheduler(rxSchedulers.ioScheduler))
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
        override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement? {
            return if (src == null) null else JsonPrimitive(src.time / 1000)
        }
    }

    private class DateDeserializer : JsonDeserializer<Date> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
            return if (json == null || json.asLong == 0L) null else Date(json.asLong * 1000)
        }
    }

}