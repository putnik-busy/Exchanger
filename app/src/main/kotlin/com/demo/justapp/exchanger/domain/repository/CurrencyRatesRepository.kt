package com.demo.justapp.exchanger.domain.repository

import com.demo.justapp.exchanger.domain.model.CurrencyModel
import io.reactivex.Single

/**
 * Репозиторий, для выполнения операций над курсами валют
 */
interface CurrencyRatesRepository {

    /**
     * Загрузить курсы валют
     *
     * @param baseCurrency валюта, выбранная по умолчанию.
     * <p>
     * Валюта по умолчанию - такая валюта, относительно которой рассчитываются остальные курсы.
     */
    fun loadCurrencyRates(baseCurrency: String): Single<CurrencyModel>
}