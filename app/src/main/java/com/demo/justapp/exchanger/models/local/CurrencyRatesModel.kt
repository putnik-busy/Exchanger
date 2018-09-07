package com.demo.justapp.exchanger.models.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Сущность, содержащая информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
@Parcelize
data class CurrencyRatesModel(var defaultCurrency: String = "",
                              var rates: List<CurrencyRate>? = null) : Parcelable