package com.demo.justapp.exchanger.presentation.rates.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.models.local.CurrencyRate;
import com.demo.justapp.exchanger.presentation.rates.adapter.util.CurrencyRateAdapterDiffUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.demo.justapp.exchanger.presentation.rates.adapter.util.CurrencyRateAdapterDiffUtil.KEY_AMOUNT;
import static com.demo.justapp.exchanger.presentation.rates.adapter.util.CurrencyRateAdapterDiffUtil.KEY_CURRENCY;

/**
 * Адаптер для курсов валют
 *
 * @author Sergey Rodionov
 */
public class RatesAdapter extends RecyclerView.Adapter<RatesViewHolder> {

    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private final AmountChangedListener mAmountChangedListener;
    private final LinkedList<CurrencyRate> mRates;

    /**
     * Констурктор для {@link RatesAdapter}
     *
     * @param recyclerViewItemListener листенер событий клика по элементу
     */
    public RatesAdapter(@NonNull RecyclerViewItemListener recyclerViewItemListener,
                        AmountChangedListener amountChangedListener) {
        mRecyclerViewItemListener = recyclerViewItemListener;
        mAmountChangedListener = amountChangedListener;
        mRates = new LinkedList<>();
    }

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public RatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exchanger_list_item, parent, false);
        return new RatesViewHolder(view, mRecyclerViewItemListener, mAmountChangedListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position) {
        holder.bindView(mRates.get(position));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            CurrencyRate currencyRate = mRates.get(position);
            for (String key : o.keySet()) {
                if (key.equals(KEY_CURRENCY)) {
                    currencyRate.setCurrency(o.getString(KEY_AMOUNT));
                } else if (key.equals(KEY_AMOUNT)) {
                    currencyRate.setRateExchange(o.getDouble(KEY_AMOUNT));
                }
            }
            holder.bindView(currencyRate);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mRates.size();
    }

    /**
     * Добавляет список валют
     *
     * @param rates список, содержащий информацию о курсах валют
     */
    public void addRates(@NonNull List<CurrencyRate> rates) {
        final CurrencyRateAdapterDiffUtil diffCallback = new CurrencyRateAdapterDiffUtil(mRates, rates);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        mRates.clear();
        mRates.addAll(rates);
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateRates(@NonNull List<CurrencyRate> rates) {
        notifyItemRangeChanged(0, rates.size() - 1);
    }


    public void changeDefaultRate(int position) {
        Collections.swap(mRates, position, 0);
        notifyItemMoved(position, 0);
    }

    /**
     * @return возвращает курсы валют
     */
    public List<CurrencyRate> getRates() {
        return new ArrayList<>(mRates);
    }
}