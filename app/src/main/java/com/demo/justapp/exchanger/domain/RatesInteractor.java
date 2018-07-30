package com.demo.justapp.exchanger.domain;

import android.support.annotation.NonNull;

import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.domain.repository.RatesRepository;
import com.demo.justapp.exchanger.models.local.Rate;
import com.demo.justapp.exchanger.models.local.RatesModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Интерактор, предоставляющий информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
@Data
public class RatesInteractor {

    private final RatesRepository mRatesRepository;
    private final Rate mRate;

    /**
     * Конструктор для {@link RatesInteractor}
     *
     * @param ratesRepository репозиторий, для работы с курсами валют
     */
    @Inject
    public RatesInteractor(@NonNull RatesRepository ratesRepository,
                           @NonNull Rate rate) {
        mRatesRepository = ratesRepository;
        mRate = rate;
        mRate.setCurrency("EUR").setRateExchange(100);
    }

    /**
     * Загружает список курсов валют
     *
     * @return список курсов валют
     */
    public Observable<List<Rate>> loadRates() {
        return mRatesRepository.loadRates(mRate.getCurrency())
                .flatMapIterable((Function<RatesModel, Iterable<Rate>>) RatesModel::getRates)
                .map(rate1 -> rate1.setRateExchange(rate1.getRateExchange() * mRate.getRateExchange()))
                .startWith(mRate)
                .toList()
                .toObservable();
    }

    public void updateCurrentCurrency(@NonNull Rate rate) {
        mRate.setRateExchange(rate.getRateExchange())
                .setCurrency(rate.getCurrency());
    }
}
