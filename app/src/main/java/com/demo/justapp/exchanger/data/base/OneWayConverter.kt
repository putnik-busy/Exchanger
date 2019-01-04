package com.demo.justapp.exchanger.data.base

/**
 * Однонаправленный конвертор из сущности [F] в сущность [T].
 *
 * @param <F> исходная сущность
 * @param <T> конечная сущность
 */
interface OneWayConverter<F, T> {

    fun convert(from: F): T
}