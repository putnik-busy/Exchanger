package com.demo.justapp.exchanger

import android.app.Application
import com.demo.justapp.exchanger.di.application.ApplicationInjector
import timber.log.Timber

class ExchangerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ApplicationInjector.createAppComponent(this)
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}