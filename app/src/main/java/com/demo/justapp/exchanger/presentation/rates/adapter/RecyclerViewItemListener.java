package com.demo.justapp.exchanger.presentation.rates.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Слушатель событий  для {@link RecyclerView}
 *
 * @author Sergey Rodionov
 */
public interface RecyclerViewItemListener {

    /**
     * Слушатель клика по item
     *
     * @param adapterPosition позиция клика
     * @param viewType        тип вью
     */
    void onItemClick(int adapterPosition, int viewType);
}