package com.demo.justapp.exchanger.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.application.ComponentHelper.getComponent
import com.demo.justapp.exchanger.di.currencies.CurrenciesComponent
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.presentation.presenters.CurrencyRatesPresenter
import com.demo.justapp.exchanger.presentation.ui.adapter.CurrencyRatesAdapter
import com.demo.justapp.exchanger.presentation.ui.extensions.addSystemBottomPadding
import com.demo.justapp.exchanger.presentation.ui.extensions.addSystemTopPadding
import com.demo.justapp.exchanger.presentation.ui.extensions.hideKeyboard
import com.demo.justapp.exchanger.presentation.ui.view.RatesView
import kotlinx.android.synthetic.main.fragment_exchanger.emptyTextView
import kotlinx.android.synthetic.main.fragment_exchanger.loadingProgressBarView
import kotlinx.android.synthetic.main.fragment_exchanger.recyclerVew
import kotlinx.android.synthetic.main.fragment_exchanger.toolbar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

internal typealias CurrencyItemListener = (Int) -> Unit
internal typealias ChangeAmountListener = (String) -> Unit

class CurrencyRatesFragment : MvpAppCompatFragment(), RatesView {

    companion object {
        const val TAG = "CurrencyRatesFragment"

        @JvmStatic
        fun newInstance(): CurrencyRatesFragment = CurrencyRatesFragment()
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: CurrencyRatesPresenter

    private lateinit var adapter: CurrencyRatesAdapter
    private lateinit var recyclerListener: RecyclerView.OnScrollListener

    @ProvidePresenter
    fun providePresenter(): CurrencyRatesPresenter = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        getComponent(requireActivity(), CurrenciesComponent::class.java).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchanger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.addSystemTopPadding()
        prepareAdapter(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerVew.removeOnScrollListener(recyclerListener)
    }

    override fun showRates(items: List<CurrencyRate>) {
        adapter.items = items
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
        adapter = CurrencyRatesAdapter({ changeBaseCurrency(it) }, { changeAmountBaseCurrency(it) })
        with(recyclerVew) {
            addSystemBottomPadding()
            adapter = this@CurrencyRatesFragment.adapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(recyclerListener)
        }
    }

    private fun changeBaseCurrency(adapterPosition: Int) {
        presenter.onChangeDefaultCurrency(adapter.items[adapterPosition])
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