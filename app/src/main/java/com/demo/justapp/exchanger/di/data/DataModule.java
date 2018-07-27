package com.demo.justapp.exchanger.di.data;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.data.converter.RatesConverter;
import com.demo.justapp.exchanger.data.network.RestApi;
import com.demo.justapp.exchanger.data.repository.RatesRepositoryImpl;
import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.domain.repository.RatesRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Модуль {@link Module} для работы c курсами валют в приложении.
 *
 * @author Sergey Rodionov
 */
@Module
public class DataModule {

    @Data
    @Provides
    RatesRepository provideDiskRepository(@NonNull RestApi restApi,
                                          @NonNull RatesConverter ratesConverter) {
        return new RatesRepositoryImpl(restApi, ratesConverter);
    }

}