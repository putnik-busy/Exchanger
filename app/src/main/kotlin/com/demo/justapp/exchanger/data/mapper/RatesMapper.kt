package com.demo.justapp.exchanger.data.mapper

import com.demo.justapp.exchanger.data.base.Mapper
import com.demo.justapp.exchanger.data.model.RatesResponse
import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.domain.model.CurrencyModel
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import java.math.BigDecimal
import javax.inject.Inject

@Currencies
class RatesMapper @Inject constructor() : Mapper<RatesResponse, CurrencyModel> {

    override fun map(from: RatesResponse): CurrencyModel {
        val currentRates = mutableListOf<CurrencyRate>()
        from.rates.forEach { (currency, course) ->
            currentRates.add(CurrencyRate(currency, BigDecimal.valueOf(course)))
        }
        return CurrencyModel(from.base, currentRates)
    }

}