package com.demo.justapp.exchanger.data.converter

import com.demo.justapp.exchanger.data.base.AbstractOneWayConverter
import com.demo.justapp.exchanger.data.network.RestApi
import com.demo.justapp.exchanger.models.local.CurrencyRate
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel
import com.demo.justapp.exchanger.models.remote.RatesResponse
import javax.inject.Inject

/**
 * Реализация [AbstractOneWayConverter] для запроса [RestApi.getRates]
 *
 * @author Sergey Rodionov
 */
class RatesConverter @Inject constructor()
    : AbstractOneWayConverter<RatesResponse, CurrencyRatesModel>() {

    override fun convert(from: RatesResponse): CurrencyRatesModel {
        val rates = mutableListOf<CurrencyRate>()

        for ((key, value) in from.rates) {
            val rate = CurrencyRate(key, value)
            rates.add(rate)
        }

        return CurrencyRatesModel(from.base, rates)
    }
}