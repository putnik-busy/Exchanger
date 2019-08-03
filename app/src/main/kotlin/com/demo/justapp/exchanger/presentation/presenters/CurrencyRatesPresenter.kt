package com.demo.justapp.exchanger.presentation.presenters

import com.demo.justapp.exchanger.di.scope.Currencies
import com.demo.justapp.exchanger.domain.CurrencyRatesCalculator
import com.demo.justapp.exchanger.domain.CurrencyRatesInteractor
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.presentation.ui.view.RatesView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import timber.log.Timber
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
) : MvpPresenter<RatesView>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var currencyRate: CurrencyRate = CurrencyRate(BASE_CURRENCY, BASE_CURRENCY_AMOUNT)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCurrencyRates(currencyRate.currency)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun onChangeAmountCurrency(course: String) {
        currencyRate.course = parseCourse(course)
    }

    fun onChangeDefaultCurrency(currencyRate: CurrencyRate) {
        this.currencyRate = currencyRate
        compositeDisposable.clear()
        loadCurrencyRates(currencyRate.currency)
    }

    private fun loadCurrencyRates(baseCurrency: String) {
        viewState.showProgress(true)
        compositeDisposable.add(currencyRatesInteractor.loadCurrencyRates(baseCurrency)
                .observeOn(Schedulers.computation())
                .flatMap { currencyRatesCalculator.calculate(it.exchangeRate, currencyRate.course) }
                .flatMap(addDefaultCurrencyRatesToList())
                .repeatWhen { it.delay(1, TimeUnit.SECONDS) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            viewState.showProgress(false)
                            viewState.showRates(it)
                        },
                        {
                            Timber.e(it)
                            viewState.showProgress(false)
                            viewState.showStub()
                        }))
    }

    private fun addDefaultCurrencyRatesToList(): Function<List<CurrencyRate>, Single<List<CurrencyRate>>> {
        return Function { currencies ->
            Single.just(listOf(currencyRate) + currencies)
        }
    }

    private fun parseCourse(course: String): BigDecimal {
        var result = BigDecimal.ZERO
        if (course.isNotEmpty()) {
            result = BigDecimal(course.trimEnd('.'))
        }
        return result
    }

}