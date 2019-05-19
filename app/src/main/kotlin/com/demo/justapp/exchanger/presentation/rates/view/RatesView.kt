package com.demo.justapp.exchanger.presentation.rates.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.demo.justapp.exchanger.models.local.CurrencyRate
import com.demo.justapp.exchanger.presentation.base.BaseView

/**
 * Вью списка валют
 *
 * @author Sergey Rodionov
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface RatesView : BaseView {

    /**
     * Отобразить список валют
     *
     * @param list список валют для отображения
     */
    fun showRates(list: List<CurrencyRate>)

    /**
     * Обновить список валют
     *
     * @param list список валют для отображения
     */
    fun updateRates(list: List<CurrencyRate>)

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

    /**
     * Показать сообщение об ошибке
     *
     * @param message текст ошибки
     */
    @StateStrategyType(SkipStrategy::class)
    fun showErrorMessage(message: String)

}