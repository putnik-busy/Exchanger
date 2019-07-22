package com.demo.justapp.exchanger.di.application

import com.demo.justapp.exchanger.ExchangerApp

object ApplicationInjector {

    internal lateinit var applicationComponent: ApplicationComponent

    fun createAppComponent(application: ExchangerApp): ApplicationComponent {
        applicationComponent = DaggerApplicationComponent
                .factory()
                .create(application.applicationContext)

        return applicationComponent
    }

}