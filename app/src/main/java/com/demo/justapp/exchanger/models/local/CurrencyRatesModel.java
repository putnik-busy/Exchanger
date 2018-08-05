package com.demo.justapp.exchanger.models.local;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Сущность, содержащая информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
public class CurrencyRatesModel implements Parcelable {

    private String mDefaultCurrency;
    private List<CurrencyRate> mRates;

    public CurrencyRatesModel() {
        //необходим для Parcelable
    }

    public CurrencyRatesModel(Parcel in) {
        mDefaultCurrency = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDefaultCurrency);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CurrencyRatesModel> CREATOR = new Creator<CurrencyRatesModel>() {
        @Override
        public CurrencyRatesModel createFromParcel(Parcel in) {
            return new CurrencyRatesModel(in);
        }

        @Override
        public CurrencyRatesModel[] newArray(int size) {
            return new CurrencyRatesModel[size];
        }
    };

    public String getDefaultCurrency() {
        return mDefaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        mDefaultCurrency = defaultCurrency;
    }

    public List<CurrencyRate> getRates() {
        return mRates;
    }

    public void setRates(List<CurrencyRate> rates) {
        mRates = rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyRatesModel that = (CurrencyRatesModel) o;

        if (mDefaultCurrency != null ? !mDefaultCurrency.equals(that.mDefaultCurrency) : that.mDefaultCurrency != null) {
            return false;
        }
        return mRates != null ? mRates.equals(that.mRates) : that.mRates == null;
    }

    @Override
    public int hashCode() {
        int result = mDefaultCurrency != null ? mDefaultCurrency.hashCode() : 0;
        result = 31 * result + (mRates != null ? mRates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CurrencyRatesModel{" +
                "mDefaultCurrency='" + mDefaultCurrency + '\'' +
                ", mRates=" + mRates +
                '}';
    }
}
