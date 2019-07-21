package com.demo.justapp.exchanger.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.currencies.CurrenciesComponent
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.extensions.hideKeyboard
import com.demo.justapp.exchanger.presentation.presenters.CurrencyRatesPresenter
import com.demo.justapp.exchanger.presentation.ui.adapter.CurrencyRatesAdapter
import com.demo.justapp.exchanger.presentation.ui.base.BaseFragment
import com.demo.justapp.exchanger.presentation.ui.view.RatesView
import kotlinx.android.synthetic.main.fragment_exchanger.*
import javax.inject.Inject

internal typealias CurrencyItemListener = (Int) -> Unit
internal typealias ChangeAmountListener = (String) -> Unit

class RatesFragment : BaseFragment(), RatesView {

    companion object {
        const val TAG = "RatesFragment"

        @JvmStatic
        fun newInstance(): RatesFragment = RatesFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: CurrencyRatesPresenter

    private lateinit var currencyAdapter: CurrencyRatesAdapter
    private lateinit var recyclerListener: RecyclerView.OnScrollListener

    @ProvidePresenter
    fun providePresenter(): CurrencyRatesPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        getComponent(CurrenciesComponent::class.java).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchanger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareAdapter(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerVew.removeOnScrollListener(recyclerListener)
    }

    override fun showRates(items: List<CurrencyRate>) {
        currencyAdapter.items = items
    }

    override fun showProgress(loading: Boolean) {
        loadingProgressBarView.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun showStub() {
        emptyTextView.visibility = View.VISIBLE
        recyclerVew.visibility = View.GONE
    }

    private fun prepareAdapter(view: View) {
        initScrollListener(view)
        currencyAdapter = CurrencyRatesAdapter({ changeBaseCurrency(it) }, { changeAmountBaseCurrency(it) })
        with(recyclerVew) {
            adapter = currencyAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(recyclerListener)
        }
    }

    private fun changeBaseCurrency(adapterPosition: Int) {
        presenter.onChangeDefaultCurrency(currencyAdapter.items[adapterPosition])
    }

    private fun changeAmountBaseCurrency(course: String) {
        presenter.onChangeAmountCurrency(course)
    }

    private fun initScrollListener(view: View) {
        recyclerListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    view.hideKeyboard()
                }
            }
        }
    }

}