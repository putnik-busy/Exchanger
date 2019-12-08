package com.demo.justapp.exchanger.di.currencies

import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.presentation.ui.activity.AppActivity
import com.demo.justapp.exchanger.presentation.ui.fragment.CurrencyRatesFragment
import dagger.Subcomponent

@Currencies
@Subcomponent(modules = [CurrenciesModule::class])
interface CurrenciesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CurrenciesComponent
    }

    fun inject(activity: AppActivity)
    fun inject(fragment: CurrencyRatesFragment)
}