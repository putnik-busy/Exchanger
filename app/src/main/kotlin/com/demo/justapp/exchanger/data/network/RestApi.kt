package com.demo.justapp.exchanger.data.network

import com.demo.justapp.exchanger.models.remote.RatesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Интерфейс API для работы с сервером
 *
 * @author Sergey Rodionov
 */
interface RestApi {

    @GET("latest")
    fun getRates(@Query("base") defaultCurrency: String): Single<RatesResponse>
}