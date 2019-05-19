package com.demo.justapp.exchanger.data.model

import com.demo.justapp.exchanger.data.converter.OneWayConverter
import com.demo.justapp.exchanger.domain.model.CurrencyModel
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class RatesResponse(
        @SerializedName("base")
        val base: String,
        @SerializedName("date")
        val date: String,
        @SerializedName("rates")
        val rates: LinkedHashMap<String, Double>) : OneWayConverter<CurrencyModel> {

    override fun convert(): CurrencyModel {
        val currentRates = mutableListOf<CurrencyRate>()
        rates.forEach { (currency, course) ->
            currentRates.add(CurrencyRate(currency, BigDecimal.valueOf(course)))
        }
        return CurrencyModel(base, currentRates)
    }

}