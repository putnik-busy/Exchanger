package com.demo.justapp.exchanger.di.application;

import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Инжектор для компонента {@link AppComponent}
 *
 * @author Sergey Rodionov
 */
public class AppInjector {

    private AppInjector() {
        throw new UnsupportedOperationException("Не надо создавать экземпляр класса");
    }

    private static AppComponent mAppComponent;

    /**
     * Инициализация {@link AppComponent} компонента
     *
     * @param application аппликейшн
     * @return {@link AppComponent}
     */
    public static AppComponent createAppComponent(@NonNull Application application) {
        mAppComponent = DaggerAppComponent.builder()
                .application(application)
                .build();
        return mAppComponent;
    }

    /**
     * @return {@link AppComponent}
     */
    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

}
