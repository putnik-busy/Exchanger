package com.demo.justapp.exchanger.presentation.rates.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.models.local.Rate;

import java.text.DecimalFormat;

/**
 * Холдер для ленты фото
 *
 * @author Sergey Rodionov
 */
public class RatesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private TextView mRateNameTextView;
    private EditText mRateAmountEditText;

    /**
     * Констурктор для {@link RatesViewHolder}
     *
     * @param itemView                 вью элемента списка
     * @param recyclerViewItemListener листенер событий клика по элементу
     */
    RatesViewHolder(@NonNull View itemView,
                    @NonNull RecyclerViewItemListener recyclerViewItemListener) {
        super(itemView);
        mRecyclerViewItemListener = recyclerViewItemListener;
        ViewGroup itemContainer = itemView.findViewById(R.id.item_container);
        mRateNameTextView = itemView.findViewById(R.id.rate_name_text_view);
        mRateAmountEditText = itemView.findViewById(R.id.rate_amount_edit_text);
        itemContainer.setOnClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        if (mRecyclerViewItemListener != null) {
            mRecyclerViewItemListener.onItemClick(this, getAdapterPosition(), getItemViewType());
        }
    }

    /**
     * Привязывает данные из модели к вьюхам на форме
     *
     * @param rate модель, содержащая информация о фото
     */
    void bindView(Rate rate) {
        mRateNameTextView.setText(rate.getCurrency());
        String formatValue = new DecimalFormat("##.##").format(rate.getRateExchange());
        mRateAmountEditText.setText(formatValue);
    }
}
