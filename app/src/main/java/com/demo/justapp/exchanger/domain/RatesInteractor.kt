package com.demo.justapp.exchanger.domain

import android.support.annotation.NonNull
import com.demo.justapp.exchanger.domain.repository.RatesRepository
import com.demo.justapp.exchanger.models.local.CurrencyRate
import io.reactivex.Single
import javax.inject.Inject

/**
 * Интерактор, предоставляющий информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
class RatesInteractor @Inject constructor(@NonNull ratesRepository: RatesRepository) {

    private val mRatesRepository: RatesRepository = ratesRepository

    fun loadRates(@NonNull currency: String): Single<List<CurrencyRate>> {
        return mRatesRepository.loadRates(currency)
                .flattenAsObservable<CurrencyRate> { it.rates!! }
                .toList()
    }
}