package com.demo.justapp.exchanger.data.base

import android.support.annotation.NonNull
import android.support.annotation.Nullable
import io.reactivex.functions.Function
import java.util.*

/**
 *  Базовая реализация [OneWayConverter]
 */
abstract class AbstractOneWayConverter<F, T> : OneWayConverter<F, T> {

    @NonNull
    override fun convert(@NonNull from: F): T {
        throw  UnsupportedOperationException()
    }

    @NonNull
    @Throws(Exception::class)
    fun convertList(@Nullable fromList: List<F>?): List<T> {
        return iterate(fromList, Function { convert(it) })
    }

    @Throws(Exception::class)
    private fun iterate(@Nullable source: List<F>?, function: Function<F, T>): List<T> {
        if (source == null || source.isEmpty()) {
            return Collections.emptyList()
        }

        val result = ArrayList<T>()

        for (item in source) {
            result.add(function.apply(item))
        }

        return result
    }
}