package com.demo.justapp.exchanger.presentation.rates.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.di.scope.Data;
import com.demo.justapp.exchanger.domain.RatesInteractor;
import com.demo.justapp.exchanger.models.local.Rate;
import com.demo.justapp.exchanger.presentation.base.BasePresenter;
import com.demo.justapp.exchanger.presentation.rates.view.RatesView;
import com.demo.justapp.exchanger.presentation.resources.ResourceManager;
import com.demo.justapp.exchanger.rx.RxSchedulers;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
@Data
@InjectViewState
public class RatesPresenter extends BasePresenter<RatesView> {

    private final RatesInteractor mRatesInteractor;
    private final RxSchedulers mRxSchedulers;
    private final ResourceManager mResourceManager;

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
                          @NonNull ResourceManager resourceManager) {
        mRatesInteractor = ratesInteractor;
        mRxSchedulers = rxSchedulers;
        mResourceManager = resourceManager;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadRates();
    }

    /**
     * Загружает фото пользователя
     */
    public void loadRates() {
        getRxCompositeDisposable().add(mRatesInteractor
                .loadRates("EUR")
                .repeat(1000L)
                .subscribeOn(mRxSchedulers.getIOScheduler())
                .observeOn(mRxSchedulers.getMainThreadScheduler())
                .doOnSubscribe(disposable -> getViewState().showProgress(true))
                .doAfterTerminate(() -> getViewState().showProgress(false))
                .subscribe(
                        ratesModel -> {
                            List<Rate> rates = ratesModel.getRates();
                            if (rates.isEmpty()) {
                                getViewState().showStub();
                            } else {
                                getViewState().showRates(rates);
                            }
                        },
                        throwable -> {
                            String textError = mResourceManager.getString(R.string.error_load_text);
                            getViewState().showStub();
                            getViewState().showErrorMessage(textError);
                        }));
    }
}
