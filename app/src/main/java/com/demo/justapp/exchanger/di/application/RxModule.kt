package com.demo.justapp.exchanger.di.application

import com.demo.justapp.exchanger.rx.RxSchedulers
import com.demo.justapp.exchanger.rx.RxSchedulersImpl
import dagger.Binds
import dagger.Module

/**
 * Rx модуль [dagger.Module] приложения
 *
 * @author Sergey Rodionov
 */
@Module
interface RxModule {

    @Binds
    fun provideRxSchedulers(rxSchedulersImpl: RxSchedulersImpl): RxSchedulers
}