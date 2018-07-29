package com.demo.justapp.exchanger.models.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.demo.justapp.exchanger.di.scope.Data;

import javax.inject.Inject;

/**
 * @author Sergey Rodionov
 */
@Data
public class Rate implements Parcelable {

    private String mCurrency;
    private double mRateExchange;

    @Inject
    public Rate() {
        //необходим для Parcelable
    }

    public Rate(Parcel in) {
        mCurrency = in.readString();
        mRateExchange = in.readDouble();
    }

    public static final Creator<Rate> CREATOR = new Creator<Rate>() {
        @Override
        public Rate createFromParcel(Parcel in) {
            return new Rate(in);
        }

        @Override
        public Rate[] newArray(int size) {
            return new Rate[size];
        }
    };

    public String getCurrency() {
        return mCurrency;
    }

    public Rate setCurrency(String currency) {
        mCurrency = currency;
        return this;
    }

    public double getRateExchange() {
        return mRateExchange;
    }

    public Rate setRateExchange(double rateExchange) {
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
}
