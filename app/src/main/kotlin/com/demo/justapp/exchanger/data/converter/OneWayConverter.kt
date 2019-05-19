package com.demo.justapp.exchanger.data.converter

/**
 * Однонаправленный конвертор в сущность [T].
 *
 * @param [T] конечная сущность
 */
interface OneWayConverter<T> {

    fun convert(): T
}