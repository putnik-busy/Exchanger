package com.demo.justapp.exchanger.presentation.ui.adapter

import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.RecyclerView
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.presentation.ui.extensions.afterTextChanged
import com.demo.justapp.exchanger.presentation.ui.extensions.hideKeyboard
import com.demo.justapp.exchanger.presentation.ui.extensions.showKeyboard
import com.demo.justapp.exchanger.presentation.ui.fragment.ChangeAmountListener
import com.demo.justapp.exchanger.presentation.ui.fragment.CurrencyItemListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.exchanger_list_item.*

private const val HEAD_POSITION = 0

class CurrencyRatesViewHolder(
        override val containerView: View,
        private val onClickListener: CurrencyItemListener,
        private val changeAmountListener: ChangeAmountListener
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        subscribeTextChangedEvent()
        subscribeEditorActionEvent()
        subscribeFocusChangeEvent()
    }

    fun bindView(rate: CurrencyRate) {
        if (!exchangeRateEditText.hasFocus()) {
            currencyNameTextView.text = rate.currency
            exchangeRateEditText.setText("${rate.course}")
            exchangeRateEditText.setSelection(exchangeRateEditText.text.length)
        }
    }

    private fun subscribeTextChangedEvent() {
        exchangeRateEditText.afterTextChanged {
            if (exchangeRateEditText.hasFocus()) {
                changeAmountListener(it)
            }
        }
    }

    private fun subscribeEditorActionEvent() {
        exchangeRateEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                exchangeRateEditText.clearFocus()
                containerView.hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun subscribeFocusChangeEvent() {
        currencyContainer.setOnClickListener {
            if (adapterPosition > HEAD_POSITION) {
                exchangeRateEditText.requestFocus()
                exchangeRateEditText.showKeyboard()
                onClickListener(adapterPosition)
            }
        }
    }

}