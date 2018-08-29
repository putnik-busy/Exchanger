package com.demo.justapp.exchanger.data.repository

import android.support.annotation.NonNull
import com.demo.justapp.exchanger.data.converter.RatesConverter
import com.demo.justapp.exchanger.data.network.RestApi
import com.demo.justapp.exchanger.domain.repository.RatesRepository
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Реализация [RatesRepository]
 *
 * @author Sergey Rodionov
 */
class RatesRepositoryImpl @Inject constructor(@NonNull restApi: RestApi,
                                              @NonNull ratesConverter: RatesConverter) : RatesRepository {

    private val mRestApi: RestApi = restApi
    private val mRatesConverter: RatesConverter = ratesConverter

    override fun loadRates(rate: String): Single<CurrencyRatesModel> {
        return mRestApi.getRates(rate).map { mRatesConverter.convert(it) }
    }

}