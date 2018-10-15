package com.demo.justapp.exchanger.presentation.rates.adapter.util

import android.os.Bundle
import android.support.v7.util.DiffUtil
import com.demo.justapp.exchanger.models.local.CurrencyRate

/**
 * @author Sergey Rodionov
 */
class CurrencyRateAdapterDiffUtil(oldList: List<CurrencyRate>,
                                  newList: List<CurrencyRate>)
    : DiffUtil.Callback() {

    companion object {
        internal const val KEY_CURRENCY = "currency"
        internal const val KEY_AMOUNT = "amount"
    }

    private val mOldList: List<CurrencyRate> = oldList
    private val mNewList: List<CurrencyRate> = newList

    override fun getOldListSize(): Int {
        return mOldList.size
    }

    override fun getNewListSize(): Int {
        return mNewList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition] == mNewList[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrencyItem = mOldList[oldItemPosition]
        val newCurrencyItem = mNewList[newItemPosition]
        return oldCurrencyItem.equals(newCurrencyItem)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = mOldList[oldItemPosition]
        val newItem = mNewList[newItemPosition]
        val difBundle = Bundle()

        if (newItem.currency != oldItem.currency) {
            difBundle.putString(KEY_CURRENCY, newItem.currency)
        }

        if (newItem.rate != oldItem.rate) {
            difBundle.putDouble(KEY_AMOUNT, newItem.rate)
        }

        if (difBundle.size() == 0) {
            return null
        }

        return difBundle
    }

}