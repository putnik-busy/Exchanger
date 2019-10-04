package com.demo.justapp.exchanger.domain.interactor

import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.domain.model.CurrencyModel
import com.demo.justapp.exchanger.domain.repository.CurrencyRatesRepository
import io.reactivex.Single
import javax.inject.Inject

@Currencies
class CurrencyRatesInteractor @Inject constructor(
        private val currencyRatesRepository: CurrencyRatesRepository
) {

    fun loadCurrencyRates(baseCurrency: String): Single<CurrencyModel> {
        return currencyRatesRepository.loadCurrencyRates(baseCurrency)
    }

}