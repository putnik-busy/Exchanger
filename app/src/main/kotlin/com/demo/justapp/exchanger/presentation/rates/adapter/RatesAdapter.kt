package com.demo.justapp.exchanger.presentation.rates.adapter

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.models.local.CurrencyRate
import com.demo.justapp.exchanger.presentation.rates.adapter.util.CurrencyRateAdapterDiffUtil
import com.demo.justapp.exchanger.presentation.rates.adapter.util.KEY_AMOUNT
import com.demo.justapp.exchanger.presentation.rates.adapter.util.KEY_CURRENCY
import java.util.*

/**
 * Адаптер для курсов валют
 *
 * @author Sergey Rodionov
 */
class RatesAdapter(recyclerViewItemListener: RecyclerViewItemListener,
                   amountChangedListener: AmountChangedListener)
    : RecyclerView.Adapter<RatesViewHolder>() {

    private val mRecyclerViewItemListener = recyclerViewItemListener
    private val mAmountChangedListener = amountChangedListener
    private val mRates: LinkedList<CurrencyRate> = LinkedList()

    val rates: List<CurrencyRate>
        get() = ArrayList(mRates)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.exchanger_list_item, parent, false)
        return RatesViewHolder(view, mRecyclerViewItemListener, mAmountChangedListener)
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bindView(mRates[position])
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int, payloads: List<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val bundle = payloads[0] as Bundle
            val currencyRate = mRates[position]
            for (key in bundle.keySet()) {
                if (key == KEY_CURRENCY) {
                    currencyRate.currency = bundle.getString(KEY_CURRENCY)
                } else if (key == KEY_AMOUNT) {
                    currencyRate.rate = bundle.getDouble(KEY_AMOUNT)
                }
            }
            holder.bindView(currencyRate)
        }
    }

    override fun getItemCount(): Int = mRates.size

    fun addRates(rates: List<CurrencyRate>) {
        val diffCallback = CurrencyRateAdapterDiffUtil(mRates, rates)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        mRates.clear()
        mRates.addAll(rates)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateRates(rates: List<CurrencyRate>) {
        notifyItemRangeChanged(0, rates.size - 1)
    }

    fun changeDefaultRate(position: Int) {
        Collections.swap(mRates, position, 0)
        notifyItemMoved(position, 0)
    }

}