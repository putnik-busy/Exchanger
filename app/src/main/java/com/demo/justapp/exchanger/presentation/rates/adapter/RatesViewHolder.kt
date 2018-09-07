package com.demo.justapp.exchanger.presentation.rates.adapter

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.models.local.CurrencyRate
import java.lang.Double.valueOf
import java.text.DecimalFormat

/**
 * Вью холдер для список валют
 *
 * @author Sergey Rodionov
 */
class RatesViewHolder(itemView: View,
                      recyclerViewItemListener: RecyclerViewItemListener,
                      amountChangedListener: AmountChangedListener)
    : RecyclerView.ViewHolder(itemView) {

    private val mRecyclerViewItemListener: RecyclerViewItemListener = recyclerViewItemListener
    private val mAmountChangedListener: AmountChangedListener = amountChangedListener
    private val mRateNameTextView: TextView = itemView.findViewById(R.id.rate_name_text_view)
    private val mRateAmountEditText: EditText = itemView.findViewById(R.id.rate_amount_edit_text)

    init {
        mRateAmountEditText.addTextChangedListener(CurrencyTextWatcher())
        mRateAmountEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && adapterPosition > RecyclerView.TOUCH_SLOP_DEFAULT) {
                mRecyclerViewItemListener.onItemClick(adapterPosition, itemViewType)
            }
        }
    }

    fun bindView(rate: CurrencyRate) {
        if (!mRateAmountEditText.isFocused) {
            mRateNameTextView.text = rate.currency
            val formatValue: String = DecimalFormat("##.##").format(rate.rate)
            mRateAmountEditText.setText(formatValue)
        }
    }

    private inner class CurrencyTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (mRateAmountEditText.isFocused) {
                val amount: Double = try {
                    valueOf(s.toString())
                } catch (ex: NumberFormatException) {
                    0.0
                }
                mAmountChangedListener.amountChanged(amount)
            }
        }
    }

}