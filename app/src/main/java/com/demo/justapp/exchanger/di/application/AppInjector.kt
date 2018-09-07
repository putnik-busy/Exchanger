package com.demo.justapp.exchanger.di.application

import android.app.Application
import android.support.annotation.NonNull

/**
 * Инжектор для компонента [AppComponent]
 *
 * @author Sergey Rodionov
 */
class AppInjector private constructor() {

    companion object {
        private lateinit var mAppComponent: AppComponent

        @NonNull
        fun createAppComponent(application: Application): AppComponent {
            mAppComponent = DaggerAppComponent.builder()
                    .application(application)
                    .build()
            return mAppComponent
        }

        @NonNull
        fun getAppComponent() = mAppComponent
    }

}