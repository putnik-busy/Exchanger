package com.demo.justapp.exchanger.domain;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.domain.repository.RatesRepository;
import com.demo.justapp.exchanger.models.local.RatesModel;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Интерактор, предоставляющий информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
@Data
public class RatesInteractor {

    private final RatesRepository mRatesRepository;

    /**
     * Конструктор для {@link RatesInteractor}
     *
     * @param ratesRepository репозиторий, для работы с курсами валют
     */
    @Inject
    public RatesInteractor(@NonNull RatesRepository ratesRepository) {
        mRatesRepository = ratesRepository;
    }

    /**
     * Загружает список курсов валют
     *
     * @param rate выбранная валюта
     * @return список курсов валют
     */
    public Single<RatesModel> loadRates(@NonNull String rate) {
        return mRatesRepository.loadRates(rate);
    }
}
