package com.demo.justapp.exchanger.presentation.rates.adapter

/**
 * Слушатель событий  для {@link RecyclerView}
 *
 * @author Sergey Rodionov
 */
interface RecyclerViewItemListener {

    fun onItemClick(adapterPosition: Int, viewType: Int)
}