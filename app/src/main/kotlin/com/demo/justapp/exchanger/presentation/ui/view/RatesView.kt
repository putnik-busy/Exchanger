package com.demo.justapp.exchanger.presentation.ui.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.demo.justapp.exchanger.domain.model.CurrencyRate

/**
 * Вью списка валют
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface RatesView : MvpView {

    /**
     * Отобразить список валют
     *
     * @param items список валют для отображения
     */
    fun showRates(items: List<CurrencyRate>)

    /**
     * Показать прогресс загрузки
     *
     * @param loading {@code true} если загрузка идет, {@code false} иначе
     */
    fun showProgress(loading: Boolean)

    /**
     * Показать заглушку, в случае ошибки или отсутствия данных
     */
    fun showStub()

}