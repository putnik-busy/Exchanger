package com.demo.justapp.exchanger.di.application

import com.demo.justapp.exchanger.ExchangerApp

/**
 * Инжектор для компонента [AppComponent]
 *
 * @author Sergey Rodionov
 */
class AppInjector private constructor() {

    companion object {
        private lateinit var mAppComponent: AppComponent

        @JvmStatic
        fun createAppComponent(application: ExchangerApp): AppComponent {
            mAppComponent = DaggerAppComponent.builder()
                    .context(application.applicationContext)
                    .build()
            mAppComponent.inject(application)

            return mAppComponent
        }

        @JvmStatic
        fun getAppComponent() = mAppComponent
    }

}