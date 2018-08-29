package com.demo.justapp.exchanger.data.repository;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.data.converter.RatesConverter;
import com.demo.justapp.exchanger.data.network.RestApi;
import com.demo.justapp.exchanger.domain.repository.RatesRepository;

import io.reactivex.Single;

/**
 * Реализация {@link RatesRepository}
 *
 * @author Sergey Rodionov
 */
public class RatesRepositoryImpl implements RatesRepository {

    private final RestApi mRestApi;
    private final RatesConverter mRatesConverter;

    /**
     * Конструктор для {@link RatesRepositoryImpl}
     *
     * @param restApi        API для работы с сервером
     * @param ratesConverter конвертор информации о курсах валют в локальную модель
     */
    public RatesRepositoryImpl(@NonNull RestApi restApi,
                               @NonNull RatesConverter ratesConverter) {
        mRestApi = restApi;
        mRatesConverter = ratesConverter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Single<CurrencyRatesModel> loadRates(@NonNull String rate) {
        return mRestApi.getRates(rate)
                .map(mRatesConverter::convert);
    }
}
