package com.demo.justapp.exchanger.domain.model

import java.math.BigDecimal

private const val BASE_CURRENCY = "EUR"
private val BASE_CURRENCY_AMOUNT = BigDecimal.valueOf(100)

val DEFAULT = CurrencyRate(BASE_CURRENCY, BASE_CURRENCY_AMOUNT)

data class CurrencyRate(
        var currency: String,
        var course: BigDecimal
)