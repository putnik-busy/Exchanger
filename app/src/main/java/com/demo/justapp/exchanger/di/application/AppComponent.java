package com.demo.justapp.exchanger.di.application;

import android.app.Application;

import com.demo.justapp.exchanger.ExchangerApp;
import com.demo.justapp.exchanger.di.data.DataComponent;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Основной компонент {@link Component} приложения.
 * <p>
 * Содержит модули, необходимые для работы на протяжении приложения
 *
 * @author Sergey Rodionov
 */
@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        RxModule.class,
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(ExchangerApp photoFeedApp);

    DataComponent.Builder createDataComponent();

}
