package com.demo.justapp.exchanger.domain

import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function
import java.math.BigDecimal
import java.text.NumberFormat
import javax.inject.Inject

@Currencies
class CurrencyRatesCalculator @Inject constructor() {

    private val formatter: NumberFormat by lazy {
        val numberFormat = NumberFormat.getNumberInstance()
        numberFormat.isGroupingUsed = false
        numberFormat.maximumFractionDigits = 2
        numberFormat.minimumFractionDigits = 0
        return@lazy numberFormat
    }

    fun calculate(currencies: List<CurrencyRate>, course: BigDecimal): Single<List<CurrencyRate>> {
        return Observable.fromIterable(currencies)
                .flatMapSingle(calculateRateInternal(course))
                .flatMapSingle(formatRate())
                .toList()
    }

    private fun calculateRateInternal(course: BigDecimal): Function<CurrencyRate, Single<CurrencyRate>> {
        return Function { current ->
            current.course = current.course.multiply(course)
            Single.just(current)
        }
    }

    private fun formatRate(): Function<CurrencyRate, Single<CurrencyRate>> {
        return Function { current ->
            current.course = formatLocalizedBigDecimalValue(current.course)
            Single.just(current)
        }
    }

    private fun formatLocalizedBigDecimalValue(input: BigDecimal): BigDecimal {
        return formatter.format(input).replace(",", ".").toBigDecimal()
    }

}