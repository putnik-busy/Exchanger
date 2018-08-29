package com.demo.justapp.exchanger.presentation.rates.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.demo.justapp.exchanger.presentation.base.BaseView;

import java.util.List;

/**
 * Вью списка валют
 *
 * @author Sergey Rodionov
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface RatesView extends BaseView {

    /**
     * Отобразить список валют
     *
     * @param list список валют для отображения
     */
    void showRates(@NonNull List<CurrencyRate> list);

    void updateRates(@NonNull List<CurrencyRate> list);

    /**
     * Показать прогресс загрузки
     *
     * @param loading {@code true} если загрузка идет, {@code false} иначе
     */
    void showProgress(boolean loading);

    /**
     * Показать заглушку, в случае ошибки или отсутствия данных
     */
    void showStub();

    /**
     * Показать сообщение об ошибке
     *
     * @param message текст ошибки
     */
    @StateStrategyType(SkipStrategy.class)
    void showErrorMessage(@NonNull String message);
}
