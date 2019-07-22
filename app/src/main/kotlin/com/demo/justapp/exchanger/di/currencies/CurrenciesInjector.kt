package com.demo.justapp.exchanger.di.currencies

import com.demo.justapp.exchanger.di.application.ApplicationInjector
import javax.inject.Inject

class CurrenciesInjector @Inject constructor() {

    private var currenciesComponent: CurrenciesComponent? = null

    fun getDataComponent(): CurrenciesComponent {
        return currenciesComponent ?: ApplicationInjector
                .applicationComponent
                .currenciesComponentFactory
                .create()
    }

    fun clearDataComponent() {
        currenciesComponent = null
    }

}