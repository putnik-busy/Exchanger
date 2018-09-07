package com.demo.justapp.exchanger.di.application

import com.demo.justapp.exchanger.rx.RxSchedulers
import com.demo.justapp.exchanger.rx.RxSchedulersImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Rx модуль [dagger.Module] приложения
 *
 * @author Sergey Rodionov
 */
@Module
class RxModule {

    @Singleton
    @Provides
    fun provideRxSchedulers(): RxSchedulers {
        return RxSchedulersImpl()
    }
}