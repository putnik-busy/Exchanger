package com.demo.justapp.exchanger.presentation.rates.adapter.util;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.demo.justapp.exchanger.models.local.CurrencyRate;

import java.util.List;

/**
 * @author Sergey Rodionov
 */
public class CurrencyRateAdapterDiffUtil extends DiffUtil.Callback {

    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_AMOUNT = "amount";
    private List<CurrencyRate> mOldList;
    private List<CurrencyRate> mNewList;

    /**
     * @param oldList старый лист с данными
     * @param newList новый лист с данными
     */
    public CurrencyRateAdapterDiffUtil(@NonNull List<CurrencyRate> oldList,
                                       @NonNull List<CurrencyRate> newList) {
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
        CurrencyRate oldItem = mOldList.get(oldItemPosition);
        CurrencyRate newItem = mNewList.get(newItemPosition);

        return oldItem.equals(newItem);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        CurrencyRate oldItem = mOldList.get(oldItemPosition);
        CurrencyRate newItem = mNewList.get(newItemPosition);

        Bundle diffBundle = new Bundle();
        if (!newItem.getCurrency().equals(oldItem.getCurrency())) {
            diffBundle.putString(KEY_CURRENCY, newItem.getCurrency());
        }
        if (newItem.getRateExchange() != oldItem.getRateExchange()) {
            diffBundle.putDouble(KEY_AMOUNT, oldItem.getRateExchange());
        }
        if (diffBundle.size() == 0) return null;
        return diffBundle;
    }
}
