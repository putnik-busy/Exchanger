package com.demo.justapp.exchanger.presentation.rates.adapter.util;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import com.demo.justapp.exchanger.models.local.Rate;
import com.demo.justapp.exchanger.presentation.rates.adapter.RatesViewHolder;

import java.util.List;

/**
 * @author Sergey Rodionov
 */
public class CurrencyRateAdapterDiffUtil extends DiffUtil.Callback {

    private List<Rate> mOldList;
    private List<Rate> mNewList;

    /**
     * Стандартный конструктор
     *
     * @param oldList старый лист с данными
     * @param newList новый лист с данными
     */
    public CurrencyRateAdapterDiffUtil(@NonNull List<Rate> oldList,
                                       @NonNull List<Rate> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition) == mNewList.get(newItemPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Rate oldItem = mOldList.get(oldItemPosition);
        Rate newItem = mNewList.get(newItemPosition);

        return oldItem.equals(newItem);
    }
}
