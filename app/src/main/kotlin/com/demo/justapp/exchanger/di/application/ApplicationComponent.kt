package com.demo.justapp.exchanger.di.application

import android.content.Context
import com.demo.justapp.exchanger.di.currencies.CurrenciesComponent
import com.demo.justapp.exchanger.di.currencies.CurrenciesInjector
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    val currenciesComponentFactory: CurrenciesComponent.Factory
    val currenciesInjector: CurrenciesInjector

}