package com.demo.justapp.exchanger.di.data

import com.demo.justapp.exchanger.data.repository.RatesRepositoryImpl
import com.demo.justapp.exchanger.domain.repository.RatesRepository
import dagger.Binds
import dagger.Module

/**
 * Модуль [dagger.Module] для работы c курсами валют в приложении.
 *
 * @author Sergey Rodionov
 */
@Module
interface DataModule {

    @Binds
    fun provideRatesRepository(ratesRepository: RatesRepositoryImpl): RatesRepository
}