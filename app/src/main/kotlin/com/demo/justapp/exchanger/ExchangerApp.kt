package com.demo.justapp.exchanger

import android.app.Application
import com.demo.justapp.exchanger.di.application.AppInjector

/**
 * @author Sergey Rodionov
 */
class ExchangerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInjector.createAppComponent(this)
    }

}