package com.demo.justapp.exchanger.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.extensions.afterTextChanged
import com.demo.justapp.exchanger.extensions.hideKeyboard
import com.demo.justapp.exchanger.presentation.ui.fragment.ChangeAmountListener
import com.demo.justapp.exchanger.presentation.ui.fragment.CurrencyItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.exchanger_list_item.*
import java.lang.Double.valueOf

class CurrencyRatesViewHolder(
        override val containerView: View,
        private val onClickListener: CurrencyItemListener,
        private val changeAmountListener: ChangeAmountListener
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        exchangeRateEditText.clearFocus()
        exchangeRateEditText.afterTextChanged {
            if (exchangeRateEditText.isFocused) {
                val amount: Double = try {
                    valueOf(it)
                } catch (ex: NumberFormatException) {
                    0.0
                }
                changeAmountListener(amount)
            }
        }
        exchangeRateEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                exchangeRateEditText.clearFocus()
                containerView.apply { context.hideKeyboard(this) }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        exchangeRateEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && adapterPosition > RecyclerView.TOUCH_SLOP_DEFAULT) {
                onClickListener(adapterPosition)
            }
        }
    }

    fun bindView(rate: CurrencyRate) {
        if (!exchangeRateEditText.isFocused) {
            currencyNameTextView.text = rate.currency
            exchangeRateEditText.setText("${rate.course}")
        }
    }

}