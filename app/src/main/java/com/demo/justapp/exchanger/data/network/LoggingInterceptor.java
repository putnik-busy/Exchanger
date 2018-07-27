package com.demo.justapp.exchanger.data.network;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Реализация {@link Interceptor}, предназначенная для логгирования запросов/ответов к серверу
 *
 * @author Sergey Rodionov
 */
public class LoggingInterceptor implements Interceptor {

    private static final String TAG = "LoggingInterceptor";

    private final HttpLoggingInterceptor mHttpLoggingInterceptor;

    public LoggingInterceptor() {
        mHttpLoggingInterceptor = new HttpLoggingInterceptor(
                message -> Timber.v(message, TAG)
        ).setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY :
                HttpLoggingInterceptor.Level.NONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return mHttpLoggingInterceptor.intercept(chain);
    }

}
