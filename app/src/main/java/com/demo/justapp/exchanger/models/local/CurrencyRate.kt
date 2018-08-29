package com.demo.justapp.exchanger.models.local

import android.os.Parcel
import android.os.Parcelable
import com.demo.justapp.exchanger.di.scope.Data
import javax.inject.Inject

/**
 * @author Sergey Rodionov
 */
@Data
data class CurrencyRate constructor(var currency: String,
                                    var rate: Double?) : Parcelable {

    @Inject constructor() : this("", null)

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(currency)
        parcel.writeDouble(rate!!)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CurrencyRate> {
        override fun createFromParcel(parcel: Parcel): CurrencyRate {
            return CurrencyRate(parcel)
        }

        override fun newArray(size: Int): Array<CurrencyRate?> {
            return arrayOfNulls(size)
        }
    }
}