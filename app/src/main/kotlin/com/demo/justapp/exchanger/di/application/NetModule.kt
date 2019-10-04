package com.demo.justapp.exchanger.di.application

import com.demo.justapp.exchanger.data.interceptor.LoggingInterceptor
import com.demo.justapp.exchanger.data.api.CurrencyRatesApi
import com.google.gson.*
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://revolut.duckdns.org/"
private const val TIMEOUT = 20L

@Module
object NetModule {

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.io()))
                .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideGSON(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, DateDeserializer())
                .registerTypeAdapter(Date::class.java, DateSerializer())
                .create()
    }

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideRestApi(retrofit: Retrofit): CurrencyRatesApi {
        return retrofit.create(CurrencyRatesApi::class.java)
    }

    private class DateSerializer : JsonSerializer<Date> {
        override fun serialize(src: Date, typeOfSrc: Type, context: JsonSerializationContext): JsonPrimitive {
            return JsonPrimitive(src.time.div(1000))
        }
    }

    private class DateDeserializer : JsonDeserializer<Date> {
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date {
            return Date(json.asLong.times(1000))
        }
    }

}