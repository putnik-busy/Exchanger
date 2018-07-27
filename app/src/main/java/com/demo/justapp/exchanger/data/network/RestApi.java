package com.demo.justapp.exchanger.data.network;

import com.demo.justapp.exchanger.models.remote.RatesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Интерфейс API для работы с сервером
 *
 * @author Sergey Rodionov
 */
public interface RestApi {

    /**
     * @param defaultCurrency валюта по умолчанию
     * @return возвращает список курсов валют
     */
    @GET("latest")
    Single<RatesResponse> getRates(@Query("base") String defaultCurrency);
}
