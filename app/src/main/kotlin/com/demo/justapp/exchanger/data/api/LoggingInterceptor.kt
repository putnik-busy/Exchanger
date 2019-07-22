package com.demo.justapp.exchanger.data.api

import com.demo.justapp.exchanger.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

private const val TAG = "LoggingInterceptor"

class LoggingInterceptor : Interceptor {

    private val httpLoggingInterceptor: HttpLoggingInterceptor

    init {
        httpLoggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { message -> Timber.v(message, TAG) })
                .setLevel(
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return httpLoggingInterceptor.intercept(chain)
    }

}