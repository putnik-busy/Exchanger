package com.demo.justapp.exchanger

import com.demo.justapp.exchanger.data.model.RatesResponse
import com.demo.justapp.exchanger.domain.model.CurrencyModel
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import java.math.BigDecimal

const val BASE_CURRENCY = "EUR"
const val DATE = "22.07.2019"

object CurrencyRatesStubDataProvider {

    fun createRatesResponse(): RatesResponse {
        return RatesResponse(BASE_CURRENCY, DATE, linkedMapOf(BASE_CURRENCY to 100.00))
    }

    fun createCurrencyModel(): CurrencyModel {
        return CurrencyModel(BASE_CURRENCY, listOf(CurrencyRate(BASE_CURRENCY, BigDecimal.valueOf(100.0))))
    }

}