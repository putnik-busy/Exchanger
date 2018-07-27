package com.demo.justapp.exchanger;

import android.app.Application;

import com.demo.justapp.exchanger.di.application.AppInjector;

/**
 * @author Sergey Rodionov
 */
public class ExchangerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.createAppComponent(this).inject(this);
    }
}
