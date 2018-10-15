package com.demo.justapp.exchanger.di.data

import com.demo.justapp.exchanger.di.application.AppInjector
import javax.inject.Inject

/**
 * Инжектор для компонента [DataComponent]
 *
 * @author Sergey Rodionov
 */
class DataInjector @Inject constructor(){

    private var mDataComponent: DataComponent? = null

    fun createDataComponent(): DataComponent {
        if (mDataComponent == null) {
            mDataComponent = AppInjector.getAppComponent()
                    .createDataComponent()
                    .build()
        }
        return mDataComponent as DataComponent
    }

    fun clearDataComponent() {
        mDataComponent = null
    }

}