package com.demo.justapp.exchanger.di.application

import android.app.Application
import android.content.Context
import com.demo.justapp.exchanger.presentation.resources.ResourceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Основной модуль [dagger.Module] приложения.
 * <p>
 * Провайдит зависимости, необходимые на протяжении жизни приложения
 *
 * @author Sergey Rodionov
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideResourceManager(context: Context) = ResourceManager(context)

}