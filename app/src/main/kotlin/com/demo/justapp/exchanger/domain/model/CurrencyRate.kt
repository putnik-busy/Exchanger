package com.demo.justapp.exchanger.domain.model

import java.math.BigDecimal

data class CurrencyRate(
        var currency: String,
        var course: BigDecimal
)