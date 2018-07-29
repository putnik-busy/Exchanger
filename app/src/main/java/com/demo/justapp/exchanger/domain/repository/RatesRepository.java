package com.demo.justapp.exchanger.domain.repository;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.models.local.RatesModel;

import io.reactivex.Observable;

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
    Observable<RatesModel> loadRates(@NonNull String rate);
}
