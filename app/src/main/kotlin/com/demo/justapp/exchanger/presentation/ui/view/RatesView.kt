package com.demo.justapp.exchanger.presentation.ui.view

import com.demo.justapp.exchanger.domain.model.CurrencyRate
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

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