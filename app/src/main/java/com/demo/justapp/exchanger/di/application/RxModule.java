package com.demo.justapp.exchanger.di.application;

import com.demo.justapp.exchanger.rx.RxSchedulers;
import com.demo.justapp.exchanger.rx.RxSchedulersImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Rx модуль {@link Module} приложения
 *
 * @author Sergey Rodionov
 */
@Module
public class RxModule {

    @Singleton
    @Provides
    public RxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }
}