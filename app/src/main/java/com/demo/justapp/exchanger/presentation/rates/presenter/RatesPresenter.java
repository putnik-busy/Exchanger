package com.demo.justapp.exchanger.presentation.rates.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.domain.RatesInteractor;
import com.demo.justapp.exchanger.presentation.base.BasePresenter;
import com.demo.justapp.exchanger.presentation.rates.view.RatesView;
import com.demo.justapp.exchanger.presentation.resources.ResourceManager;
import com.demo.justapp.exchanger.rx.RxSchedulers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

import static io.reactivex.Flowable.fromIterable;
import static java.math.BigDecimal.valueOf;

/**
 * @author Sergey Rodionov
 */
@Data
@InjectViewState
public class RatesPresenter extends BasePresenter<RatesView> {

    private final RatesInteractor mRatesInteractor;
    private final RxSchedulers mRxSchedulers;
    private final ResourceManager mResourceManager;
    private final CurrencyRate mRate;

    /**
     * Конструктор для {@link RatesPresenter}
     *
     * @param ratesInteractor интерактор для операций над фото
     * @param rxSchedulers    планировщик потоков
     * @param resourceManager менеджер ресурсов
     */
    @Inject
    public RatesPresenter(@NonNull RatesInteractor ratesInteractor,
                          @NonNull RxSchedulers rxSchedulers,
                          @NonNull ResourceManager resourceManager,
                          @NonNull CurrencyRate rate) {
        mRatesInteractor = ratesInteractor;
        mRxSchedulers = rxSchedulers;
        mResourceManager = resourceManager;
        mRate = rate.setCurrency("EUR").setRateExchange(100);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadRates(mRate.getCurrency());
    }

    public void onChangeAmountCurrency(@NonNull List<CurrencyRate> rates, double amount) {
        mRate.setRateExchange(amount);
        int count = rates.size();
        for (int i = 1; i < count; i++) {
            CurrencyRate rate = rates.get(i);
            rate.setRateExchange(formattingCurrencyRate(rate.getRateExchange()));
        }
        getViewState().updateRates(rates);
    }

    public void onChangeDefaultCurrency(@NonNull String currency) {
        mRate.setCurrency(currency);
    }

    /**
     * Загрузить список валют
     */
    private void loadRates(@NonNull String currency) {
        getRxCompositeDisposable().add(mRatesInteractor.loadRates(currency)
                .repeatWhen(completed -> completed.delay(1, TimeUnit.MINUTES))
                .subscribeOn(mRxSchedulers.getIOScheduler())
                .observeOn(mRxSchedulers.getMainThreadScheduler())
                .doOnSubscribe(__ -> getViewState().showProgress(true))
                .flatMapSingle(elements ->
                        fromIterable(elements)
                                .map(element -> element.setRateExchange(
                                        formattingCurrencyRate(element.getRateExchange())))
                                .startWith(mRate)
                                .toList())
                .subscribe(getSuccessConsumerLoadRates(), getErrorConsumerLoadRates()));
    }

    private double formattingCurrencyRate(double amount) {
        return valueOf(amount).multiply(valueOf(mRate.getRateExchange())).doubleValue();
    }

    private Consumer<List<CurrencyRate>> getSuccessConsumerLoadRates() {
        return currencyRates -> {
            getViewState().showProgress(false);
            if (currencyRates.isEmpty()) {
                getViewState().showStub();
            } else {
                getViewState().showRates(currencyRates);
            }
        };
    }

    private Consumer<Throwable> getErrorConsumerLoadRates() {
        return throwable -> {
            getViewState().showProgress(false);
            String textError = mResourceManager.getString(R.string.error_load_text);
            getViewState().showStub();
            getViewState().showErrorMessage(textError);
        };
    }

}
