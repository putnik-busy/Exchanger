package com.demo.justapp.exchanger.models.local

import android.os.Parcel
import android.os.Parcelable

/**
 * Сущность, содержащая информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
data class CurrencyRatesModel(var defaultCurrency: String, var rates: List<CurrencyRate>) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.createTypedArrayList(CurrencyRate))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(defaultCurrency)
        parcel.writeTypedList(rates)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurrencyRatesModel> {
        override fun createFromParcel(parcel: Parcel): CurrencyRatesModel {
            return CurrencyRatesModel(parcel)
        }

        override fun newArray(size: Int): Array<CurrencyRatesModel?> {
            return arrayOfNulls(size)
        }
    }
}