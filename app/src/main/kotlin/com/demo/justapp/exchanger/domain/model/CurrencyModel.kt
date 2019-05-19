package com.demo.justapp.exchanger.domain.model

data class CurrencyModel(
        val baseCurrency: String,
        val exchangeRate: List<CurrencyRate>
)