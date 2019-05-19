package com.demo.justapp.exchanger.presentation.presenters

import com.arellomobile.mvp.InjectViewState
import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.domain.CurrencyRatesCalculator
import com.demo.justapp.exchanger.domain.CurrencyRatesInteractor
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.presentation.base.BasePresenter
import com.demo.justapp.exchanger.presentation.ui.view.RatesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val BASE_CURRENCY = "EUR"
private val BASE_CURRENCY_AMOUNT = BigDecimal.valueOf(100)

@Currencies
@InjectViewState
class CurrencyRatesPresenter @Inject constructor(
        private val currencyRatesInteractor: CurrencyRatesInteractor,
        private val currencyRatesCalculator: CurrencyRatesCalculator
) : BasePresenter<RatesView>() {
    private var currencyRate: CurrencyRate = CurrencyRate(BASE_CURRENCY, BASE_CURRENCY_AMOUNT)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCurrencyRates(BASE_CURRENCY)
    }

    fun onChangeAmountCurrency(rates: List<CurrencyRate>, amount: Double) {
        // currencyRate.course = amount
        for (currencyRate in rates) {
            //  currencyRate.course = calculateRateForCurrency(amount)
        }
        viewState.updateRates(rates)
    }

    fun onChangeDefaultCurrency(currency: String) {
        // currencyRate.currency = currency
    }

    private fun loadCurrencyRates(baseCurrency: String) {
        currencyRatesInteractor.loadCurrencyRates(baseCurrency)
                .observeOn(Schedulers.computation())
                .flatMap { currencyRatesCalculator.calculate(it.exchangeRate, currencyRate) }
                .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { viewState.showProgress(true) }
                .subscribe(
                        {
                            viewState.showProgress(false)
                            viewState.showRates(it)
                        },
                        {
                            viewState.showProgress(false)
                            viewState.showStub()
                        })
                .untilDestroy()
    }

}