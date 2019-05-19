package com.demo.justapp.exchanger.models.remote

import com.google.gson.annotations.SerializedName

/**
 *  Ответ от сервера, содержащий полную информацию о курсах валют
 *
 * @author Sergey Rodionov
 */
data class RatesResponse(
        @SerializedName("base")
        var base: String,
        @SerializedName("date")
        var date: String,
        @SerializedName("rates")
        var rates: Map<String, Double>)