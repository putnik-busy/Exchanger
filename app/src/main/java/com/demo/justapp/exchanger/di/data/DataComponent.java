package com.demo.justapp.exchanger.di.data;

import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.presentation.rates.activity.RatesActivity;
import com.demo.justapp.exchanger.presentation.rates.fragment.RatesFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Компонент для работы после авторизации {@link Component} в приложении.
 *
 * @author Sergey Rodionov
 */
@Data
@Subcomponent(modules = DataModule.class)
public interface DataComponent {

    @Subcomponent.Builder
    interface Builder {
        DataComponent build();
    }

    void inject(RatesActivity activity);

    void inject(RatesFragment fragment);

}