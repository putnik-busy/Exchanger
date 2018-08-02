package com.demo.justapp.exchanger.presentation.rates.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.models.local.Rate;

import java.text.DecimalFormat;
import java.util.Currency;

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
        mRateAmountEditText.addTextChangedListener(new CurrencyTextWatcher());
        itemContainer.setOnClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View v) {
        if (mRecyclerViewItemListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
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

    private  final class CurrencyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mRateAmountEditText.isFocused()) {
                try {
                  float   enteredAmount = Float.valueOf(s.toString());
                } catch (NumberFormatException ex) {

                }

            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
