package com.demo.justapp.exchanger.data.network

import com.demo.justapp.exchanger.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

/**
 * Реализация [Interceptor], предназначенная для логирования запросов/ответов к серверу
 *
 * @author Sergey Rodionov
 */
private const val TAG = "LoggingInterceptor"

class LoggingInterceptor : Interceptor {

    private val mHttpLoggingInterceptor: HttpLoggingInterceptor

    init {
        mHttpLoggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { message -> Timber.v(message, TAG) })
                .setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return mHttpLoggingInterceptor.intercept(chain)
    }

}