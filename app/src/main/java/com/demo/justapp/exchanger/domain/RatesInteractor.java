package com.demo.justapp.exchanger.domain;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.domain.repository.RatesRepository;
import com.demo.justapp.exchanger.models.local.CurrencyRate;
import com.demo.justapp.exchanger.models.local.CurrencyRatesModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

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
     * @return список курсов валют
     */
    public Single<List<CurrencyRate>> loadRates(@NonNull String currency) {
        return mRatesRepository.loadRates(currency)
                .flattenAsObservable((Function<CurrencyRatesModel, Iterable<CurrencyRate>>) CurrencyRatesModel::getRates)
                .toList();
    }

}
