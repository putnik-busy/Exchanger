package com.demo.justapp.exchanger.data.interceptor

import com.demo.justapp.exchanger.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

private const val TAG = "LoggingInterceptor"

class LoggingInterceptor : Interceptor {

    private val httpLoggingInterceptor = HttpLoggingInterceptor(TIMBER).apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    companion object {
        @JvmField
        val TIMBER: HttpLoggingInterceptor.Logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.i(message, TAG)
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return httpLoggingInterceptor.intercept(chain)
    }

}