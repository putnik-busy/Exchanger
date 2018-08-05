package com.demo.justapp.exchanger.models.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.demo.justapp.exchanger.di.scope.Data;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
@Data
public class CurrencyRate implements Parcelable {

    private String mCurrency;
    private double mRateExchange;

    @Inject
    public CurrencyRate() {
        //необходим для Parcelable
    }

    public CurrencyRate(Parcel in) {
        mCurrency = in.readString();
        mRateExchange = in.readDouble();
    }

    public static final Creator<CurrencyRate> CREATOR = new Creator<CurrencyRate>() {
        @Override
        public CurrencyRate createFromParcel(Parcel in) {
            return new CurrencyRate(in);
        }

        @Override
        public CurrencyRate[] newArray(int size) {
            return new CurrencyRate[size];
        }
    };

    public String getCurrency() {
        return mCurrency;
    }

    public CurrencyRate setCurrency(String currency) {
        mCurrency = currency;
        return this;
    }

    public double getRateExchange() {
        return mRateExchange;
    }

    public CurrencyRate setRateExchange(double rateExchange) {
        mRateExchange = rateExchange;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCurrency);
        dest.writeDouble(mRateExchange);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyRate rate = (CurrencyRate) o;

        if (Double.compare(rate.mRateExchange, mRateExchange) != 0) return false;
        return mCurrency != null ? mCurrency.equals(rate.mCurrency) : rate.mCurrency == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mCurrency != null ? mCurrency.hashCode() : 0;
        temp = Double.doubleToLongBits(mRateExchange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "mCurrency='" + mCurrency + '\'' +
                ", mRateExchange=" + mRateExchange +
                '}';
    }
}
