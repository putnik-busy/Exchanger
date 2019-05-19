package com.demo.justapp.exchanger.models.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Сущность, содержащая информацию о курсах валют
 * @property defaultCurrency базовая валюта для обмена
 * @property rates список курсов валют
 *
 * @author Sergey Rodionov
 */
@Parcelize
data class CurrencyRatesModel(var defaultCurrency: String,
                              var rates: List<CurrencyRate>) : Parcelable