package com.demo.justapp.exchanger.presentation.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.justapp.exchanger.R
import com.demo.justapp.exchanger.domain.model.CurrencyRate
import com.demo.justapp.exchanger.presentation.ui.adapter.extension.DiffUtilAdapter
import com.demo.justapp.exchanger.presentation.ui.fragment.ChangeAmountListener
import com.demo.justapp.exchanger.presentation.ui.fragment.CurrencyItemListener
import kotlin.properties.Delegates

class CurrencyRatesAdapter(
        private val onClickListener: CurrencyItemListener,
        private val changeAmountListener: ChangeAmountListener
) : RecyclerView.Adapter<CurrencyRatesViewHolder>(), DiffUtilAdapter {

    var items: List<CurrencyRate> by Delegates.observable(emptyList()) { _, oldValue, newValue ->
        autoUpdate(oldValue, newValue) { old, new -> old.currency == new.currency }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRatesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.exchanger_list_item, parent, false)
        return CurrencyRatesViewHolder(view, onClickListener, changeAmountListener)
    }

    override fun onBindViewHolder(holderCurrency: CurrencyRatesViewHolder, position: Int) {
        holderCurrency.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size

}