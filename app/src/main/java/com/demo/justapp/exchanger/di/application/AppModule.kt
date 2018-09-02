package com.demo.justapp.exchanger.di.application

import android.app.Application
import android.content.Context
import android.support.annotation.NonNull
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
    fun provideAppContext(@NonNull application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideResourceManager(@NonNull context: Context): ResourceManager {
        return ResourceManager(context)
    }

}