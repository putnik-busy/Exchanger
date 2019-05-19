package com.demo.justapp.exchanger.di.application

import android.content.Context
import com.demo.justapp.exchanger.ExchangerApp
import com.demo.justapp.exchanger.di.data.DataComponent
import com.demo.justapp.exchanger.di.data.DataInjector
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Основной компонент [Component] приложения.
 * <p>
 * Содержит модули, необходимые для работы на протяжении приложения
 *
 * @author Sergey Rodionov
 */
@Singleton
@Component(modules = [
    AppModule::class,
    NetModule::class,
    RxModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(exchangerApp: ExchangerApp)

    fun createDataComponent(): DataComponent.Builder

    fun getDataInjector(): DataInjector

}