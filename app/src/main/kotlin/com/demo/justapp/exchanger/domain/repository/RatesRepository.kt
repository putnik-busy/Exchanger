package com.demo.justapp.exchanger.domain.repository

import android.support.annotation.NonNull
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel
import io.reactivex.Single

/**
 * Репозиторий, предоставляющий операции над курсами валют
 *
 * @author Sergey Rodionov
 */
interface RatesRepository {

    fun loadRates(@NonNull rate: String): Single<CurrencyRatesModel>
}