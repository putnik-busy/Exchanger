package com.demo.justapp.exchanger.data.repository

import com.demo.justapp.exchanger.data.converter.RatesConverter
import com.demo.justapp.exchanger.data.network.RestApi
import com.demo.justapp.exchanger.di.scope.Data
import com.demo.justapp.exchanger.domain.repository.RatesRepository
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel
import com.demo.justapp.exchanger.models.remote.RatesResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Реализация [RatesRepository]
 *
 * @author Sergey Rodionov
 */
@Data
class RatesRepositoryImpl @Inject constructor(restApi: RestApi,
                                              ratesConverter: RatesConverter) : RatesRepository {

    private val mRestApi: RestApi = checkNotNull(restApi)
    private val mRatesConverter: RatesConverter = checkNotNull(ratesConverter)

    override fun loadRates(rate: String): Single<CurrencyRatesModel> {
        return mRestApi.getRates(rate).map { ratesResponse: RatesResponse ->
            mRatesConverter.convert(ratesResponse)
        }
    }

}