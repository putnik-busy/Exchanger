package com.demo.justapp.exchanger.presentation.rates.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.justapp.exchanger.R;
import com.demo.justapp.exchanger.models.local.CurrencyRate;

import java.text.DecimalFormat;

/**
 * Холдер для список валют
 *
 * @author Sergey Rodionov
 */
public class RatesViewHolder extends RecyclerView.ViewHolder {

    private final RecyclerViewItemListener mRecyclerViewItemListener;
    private final AmountChangedListener mAmountChangedListener;
    private TextView mRateNameTextView;
    private EditText mRateAmountEditText;

    /**
     * @param itemView                 вью элемента списка
     * @param recyclerViewItemListener листенер событий клика по элементу
     */
    RatesViewHolder(@NonNull View itemView,
                    @NonNull RecyclerViewItemListener recyclerViewItemListener,
                    @NonNull AmountChangedListener amountChangedListener) {
        super(itemView);
        mRecyclerViewItemListener = recyclerViewItemListener;
        mAmountChangedListener = amountChangedListener;
        mRateNameTextView = itemView.findViewById(R.id.rate_name_text_view);
        mRateAmountEditText = itemView.findViewById(R.id.rate_amount_edit_text);
        mRateAmountEditText.addTextChangedListener(new CurrencyTextWatcher());
        mRateAmountEditText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus
                    && getAdapterPosition() > RecyclerView.TOUCH_SLOP_DEFAULT) {
                mRecyclerViewItemListener.onItemClick(getAdapterPosition(), getItemViewType());
            }
        });
    }

    /**
     * Привязывает данные из модели к вьюхам на форме
     *
     * @param rate модель, содержащая информация о фото
     */
    void bindView(CurrencyRate rate) {
        if (!mRateAmountEditText.isFocused()) {
            mRateNameTextView.setText(rate.getCurrency());
            String formatValue = new DecimalFormat("##.##").format(rate.getRate());
            mRateAmountEditText.setText(formatValue);
        }
    }

    private final class CurrencyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (mRateAmountEditText.isFocused()) {
                double amount;
                try {
                    amount = Double.valueOf(s.toString());
                } catch (NumberFormatException e) {
                    amount = 0;
                }
                mAmountChangedListener.amountChanged(amount);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
