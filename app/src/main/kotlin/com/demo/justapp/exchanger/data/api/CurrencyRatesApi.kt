package com.demo.justapp.exchanger.data.api

import com.demo.justapp.exchanger.data.model.RatesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Api загрузки валют
 */
interface CurrencyRatesApi {

    @GET("latest")
    fun getCurrencyRates(@Query("base") baseCurrency: String): Single<RatesResponse>
}