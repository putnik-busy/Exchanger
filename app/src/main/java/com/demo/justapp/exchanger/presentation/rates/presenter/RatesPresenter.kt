package com.demo.justapp.exchanger.presentation.rates.presenter

import com.arellomobile.mvp.InjectViewState
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.domain.RatesInteractor
import com.demo.justapp.exchanger.models.local.CurrencyRate
import com.demo.justapp.exchanger.presentation.base.BasePresenter
import com.demo.justapp.exchanger.presentation.rates.view.RatesView
import com.demo.justapp.exchanger.presentation.resources.ResourceManager
import com.demo.justapp.exchanger.rx.RxSchedulers
import io.reactivex.Flowable.fromIterable
import io.reactivex.functions.Consumer
import java.math.BigDecimal.valueOf
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Sergey Rodionov
 */
@InjectViewState
class RatesPresenter @Inject constructor(
        ratesInteractor: RatesInteractor,
        rxSchedulers: RxSchedulers,
        resourceManager: ResourceManager,
        currencyRate: CurrencyRate) : BasePresenter<RatesView>() {

    private val mRatesInteractor: RatesInteractor = ratesInteractor
    private val mRxSchedulers: RxSchedulers = rxSchedulers
    private val mResourceManager: ResourceManager = resourceManager
    private val mCurrencyRate: CurrencyRate = currencyRate

    init {
        currencyRate.currency = "EUR"
        currencyRate.rate = 100.0
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadRates(mCurrencyRate.currency)
    }

    fun onChangeAmountCurrency(rates: List<CurrencyRate>, amount: Double) {
        mCurrencyRate.rate = amount
        val count: Int = rates.size
        for (i in 1 until count) {
            val rate: CurrencyRate = rates[i]
            rate.rate = formattingCurrencyRate(amount)
        }
        viewState.updateRates(rates)
    }

    fun onChangeDefaultCurrency(currency: String) {
        mCurrencyRate.currency = currency
    }

    private fun loadRates(currency: String) {
        getRxCompositeDisposable().add(
                mRatesInteractor.loadRates(currency)
                        .repeatWhen { it -> it.delay(1, TimeUnit.SECONDS) }
                        .subscribeOn(mRxSchedulers.getIOScheduler())
                        .observeOn(mRxSchedulers.getMainThreadScheduler())
                        .doOnSubscribe { _ -> viewState.showProgress(true) }
                        .flatMapSingle { elements ->
                            fromIterable(elements)
                                    .map { element ->
                                        element.rate = formattingCurrencyRate(element.rate)
                                        element
                                    }
                                    .startWith(mCurrencyRate)
                                    .toList()
                        }
                        .subscribe(getSuccessConsumerLoadRates(), getErrorConsumerLoadRates()))
    }

    private fun formattingCurrencyRate(amount: Double): Double {
        return valueOf(amount).multiply(valueOf(mCurrencyRate.rate)).toDouble()
    }

    private fun getSuccessConsumerLoadRates(): Consumer<List<CurrencyRate>> {
        return Consumer { currencyRates ->
            viewState.showProgress(false)
            if (currencyRates.isEmpty()) {
                viewState.showStub()
            } else {
                viewState.showRates(currencyRates)
            }
        }
    }

    private fun getErrorConsumerLoadRates(): Consumer<Throwable> {
        return Consumer { _ ->
            viewState.showProgress(false)
            viewState.showStub()
            viewState.showErrorMessage(mResourceManager.getString(R.string.error_load_text))
        }
    }

}