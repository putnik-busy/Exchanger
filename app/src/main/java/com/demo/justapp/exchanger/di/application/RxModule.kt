package com.demo.justapp.exchanger.di.application

import com.demo.justapp.exchanger.rx.RxSchedulers
import com.demo.justapp.exchanger.rx.RxSchedulersImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Rx модуль [dagger.Module] приложения
 *
 * @author Sergey Rodionov
 */
@Module
interface RxModule {

    @Singleton
    @Binds
    fun provideRxSchedulers(rxSchedulersImpl: RxSchedulersImpl): RxSchedulers
}