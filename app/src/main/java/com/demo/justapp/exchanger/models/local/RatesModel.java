package com.demo.justapp.exchanger.models.local;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Сущность, содержащая информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
public class RatesModel implements Parcelable {

    private String mDefaultCurrency;
    private List<Rate> mRates;

    public RatesModel() {
        //необходим для Parcelable
    }

    public RatesModel(Parcel in) {
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

    public static final Creator<RatesModel> CREATOR = new Creator<RatesModel>() {
        @Override
        public RatesModel createFromParcel(Parcel in) {
            return new RatesModel(in);
        }

        @Override
        public RatesModel[] newArray(int size) {
            return new RatesModel[size];
        }
    };

    public String getDefaultCurrency() {
        return mDefaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        mDefaultCurrency = defaultCurrency;
    }

    public List<Rate> getRates() {
        return mRates;
    }

    public void setRates(List<Rate> rates) {
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

        RatesModel that = (RatesModel) o;

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
        return "RatesModel{" +
                "mDefaultCurrency='" + mDefaultCurrency + '\'' +
                ", mRates=" + mRates +
                '}';
    }
}
