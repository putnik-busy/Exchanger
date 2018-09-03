package com.demo.justapp.exchanger.models.local

import android.os.Parcelable
import com.demo.justapp.exchanger.di.scope.Data
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

/**
 * @author Sergey Rodionov
 */
@Data
@Parcelize
data class CurrencyRate constructor(var currency: String,
                                    var rate: Double) : Parcelable {
    @Inject constructor() : this("", 0.0)
}