package com.demo.justapp.exchanger.di.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.presentation.resources.ResourceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Основной модуль {@link Module} приложения.
 * <p>
 * Провайдит зависимости, необходимые на протяжении жизни приложения
 *
 * @author Sergey Rodionov
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideAppContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    ResourceManager provideResourceManager(@NonNull Context context) {
        return new ResourceManager(context);
    }

}