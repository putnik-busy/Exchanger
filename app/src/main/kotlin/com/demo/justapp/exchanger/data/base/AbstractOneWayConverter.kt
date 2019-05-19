package com.demo.justapp.exchanger.data.base

import io.reactivex.functions.Function

/**
 *  Базовая реализация [OneWayConverter]
 */
abstract class AbstractOneWayConverter<F, T> : OneWayConverter<F, T> {

    override fun convert(from: F): T {
        throw  UnsupportedOperationException()
    }

    @Throws(Exception::class)
    fun convertList(fromList: List<F>?): List<T> {
        return iterate(fromList, Function { convert(it) })
    }

    @Throws(Exception::class)
    private fun iterate(source: List<F>?, function: Function<F, T>): List<T> {
        if (source.isNullOrEmpty()) return emptyList()

        val result = mutableListOf<T>()

        for (item in source) {
            result.add(function.apply(item))
        }

        return result
    }
}