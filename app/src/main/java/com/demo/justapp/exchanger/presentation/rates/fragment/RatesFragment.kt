package com.demo.justapp.exchanger.presentation.rates.fragment

import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.di.data.DataComponent
import com.demo.justapp.exchanger.models.local.CurrencyRate
import com.demo.justapp.exchanger.presentation.base.BaseFragment
import com.demo.justapp.exchanger.presentation.rates.adapter.AmountChangedListener
import com.demo.justapp.exchanger.presentation.rates.adapter.RatesAdapter
import com.demo.justapp.exchanger.presentation.rates.adapter.RecyclerViewItemListener
import com.demo.justapp.exchanger.presentation.rates.presenter.RatesPresenter
import com.demo.justapp.exchanger.presentation.rates.view.RatesView
import javax.inject.Inject
import javax.inject.Provider

/**
 * Фрагмент курсов валют
 *
 * @author Sergey Rodionov
 */
class RatesFragment : BaseFragment(),
        RatesView, RecyclerViewItemListener, AmountChangedListener {

    companion object {
        const val TAG = "RatesFragment"

        fun newInstance(): RatesFragment {
            return RatesFragment()
        }
    }

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mEmptyTextView: TextView
    private lateinit var mProgressBar: ContentLoadingProgressBar
    private lateinit var mRatesAdapter: RatesAdapter

    @Inject
    lateinit var mProviderRatesPresenter: Provider<RatesPresenter>
    @InjectPresenter
    lateinit var mRatesPresenter: RatesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        getComponent(DataComponent::class.java).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_exchanger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        prepareAdapter()
        mRatesPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRatesPresenter.detachView(this)
    }

    override fun onItemClick(adapterPosition: Int, viewType: Int) {
        val rate: CurrencyRate = mRatesAdapter.rates[adapterPosition]
        mRatesPresenter.onChangeDefaultCurrency(rate.currency)
        mRatesAdapter.changeDefaultRate(adapterPosition)
    }

    override fun amountChanged(amount: Double) {
        mRatesPresenter.onChangeAmountCurrency(mRatesAdapter.rates, amount)
    }

    override fun showRates(list: List<CurrencyRate>) {
        mRatesAdapter.addRates(list)
    }

    override fun updateRates(list: List<CurrencyRate>) {
        mRatesAdapter.updateRates(list)
    }

    override fun showProgress(loading: Boolean) {
        mProgressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun showStub() {
        mEmptyTextView.visibility = View.VISIBLE
        mRecyclerView.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @ProvidePresenter
    fun providePresenter(): RatesPresenter = mProviderRatesPresenter.get()

    private fun initView(view: View) {
        mRecyclerView = view.findViewById(R.id.recycler_view)
        mEmptyTextView = view.findViewById(R.id.empty_text_view)
        mProgressBar = view.findViewById(R.id.loading_progress_bar)
    }

    private fun prepareAdapter() {
        mRatesAdapter = RatesAdapter(this, this)
        mRecyclerView.adapter = mRatesAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

}