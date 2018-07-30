package com.demo.justapp.exchanger.presentation.rates.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.models.local.Rate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static android.os.Debug.waitForDebugger;

/**
 * Адаптер для курсов валют
 *
 * @author Sergey Rodionov
 */
public class RatesAdapter extends RecyclerView.Adapter<RatesViewHolder> {

    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private final LinkedList<Rate> mRates;

    /**
     * Констурктор для {@link RatesAdapter}
     *
     * @param recyclerViewItemListener листенер событий клика по элементу
     */
    public RatesAdapter(@NonNull RecyclerViewItemListener recyclerViewItemListener) {
        mRecyclerViewItemListener = recyclerViewItemListener;
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
        return new RatesViewHolder(view, mRecyclerViewItemListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(@NonNull RatesViewHolder holder, int position) {
        holder.bindView(mRates.get(position));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return mRates.size();
    }

    /**
     * Добавляет список фото для отображения
     *
     * @param rates список, содержащий информацию о курсах валют
     */
    public void addRates(@NonNull List<Rate> rates) {
        mRates.clear();
        mRates.addAll(rates);
        notifyDataSetChanged();
    }

    public void updateRate(int position) {
        Rate item = mRates.get(position);
        mRates.remove(position);
        mRates.add(0, item);
        notifyItemMoved(position, 0);
    }

    /**
     * @return возвращает курсы валют
     */
    public List<Rate> getRates() {
        return new ArrayList<>(mRates);
    }
}