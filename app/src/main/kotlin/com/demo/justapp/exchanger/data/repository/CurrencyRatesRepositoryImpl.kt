package com.demo.justapp.exchanger.data.repository

import com.demo.justapp.exchanger.data.api.CurrencyRatesApi
import com.demo.justapp.exchanger.data.mapper.RatesMapper
import com.demo.justapp.exchanger.domain.model.CurrencyModel
import com.demo.justapp.exchanger.domain.repository.CurrencyRatesRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrencyRatesRepositoryImpl @Inject constructor(
        private val currencyRatesApi: CurrencyRatesApi,
        private val ratesMapper: RatesMapper
) : CurrencyRatesRepository {

    override fun loadCurrencyRates(baseCurrency: String): Single<CurrencyModel> {
        return currencyRatesApi.getCurrencyRates(baseCurrency).map { ratesMapper.map(it) }
    }

}