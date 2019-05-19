package com.demo.justapp.exchanger

import android.app.Application
import com.demo.justapp.exchanger.di.application.ApplicationInjector

class ExchangerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationInjector.createAppComponent(this)
    }

}