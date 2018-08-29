package com.demo.justapp.exchanger.domain.repository;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.models.local.CurrencyRatesModel;

import io.reactivex.Single;

/**
 * Репозиторий, предоставляющий операции над курсами валют
 *
 * @author Sergey Rodionov
 */
public interface RatesRepository {

    /**
     * Загружает информацию о курсах валют
     *
     * @param rate выбранная валюта
     * @return информация о курсах валют
     */
    Single<CurrencyRatesModel> loadRates(@NonNull String rate);
}
