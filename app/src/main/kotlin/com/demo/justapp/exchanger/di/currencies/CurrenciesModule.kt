package com.demo.justapp.exchanger.di.currencies

import com.demo.justapp.exchanger.data.repository.CurrencyRatesRepositoryImpl
import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.domain.repository.CurrencyRatesRepository
import dagger.Binds
import dagger.Module

@Module
interface CurrenciesModule {

    @Binds
    @Currencies
    fun provideRatesRepository(repository: CurrencyRatesRepositoryImpl): CurrencyRatesRepository
}