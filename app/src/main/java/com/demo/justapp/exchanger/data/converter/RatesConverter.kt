package com.demo.justapp.exchanger.data.converter

import android.support.annotation.NonNull
import com.demo.justapp.exchanger.data.base.AbstractOneWayConverter
import com.demo.justapp.exchanger.models.local.CurrencyRate
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel
import com.demo.justapp.exchanger.models.remote.RatesResponse
import javax.inject.Inject

/**
 * Реализация [AbstractOneWayConverter] для запроса {@link RestApi#getRates(String)}()}
 *
 * @author Sergey Rodionov
 */
class RatesConverter @Inject constructor()
    : AbstractOneWayConverter<RatesResponse, CurrencyRatesModel>() {

    @NonNull
    override fun convert(@NonNull from: RatesResponse): CurrencyRatesModel {
        val currencyRatesModel = CurrencyRatesModel()
        val rates = ArrayList<CurrencyRate>()

        currencyRatesModel.defaultCurrency = from.base

        for (entry in from.rates.entries) {
            val rate = CurrencyRate(entry.key, entry.value)
            rates.add(rate)
        }

        currencyRatesModel.rates = rates
        return currencyRatesModel
    }
}