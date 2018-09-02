package com.demo.justapp.exchanger.di.data

import com.demo.justapp.exchanger.di.scope.Data
import com.demo.justapp.exchanger.presentation.rates.activity.RatesActivity
import com.demo.justapp.exchanger.presentation.rates.fragment.RatesFragment
import dagger.Subcomponent

/**
 * Компонент для работы после авторизации [dagger.Component] в приложении.
 *
 * @author Sergey Rodionov
 */
@Data
@Subcomponent(modules = [DataModule::class])
interface DataComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): DataComponent
    }

    fun inject(activity: RatesActivity)
    fun inject(fragment: RatesFragment)
}