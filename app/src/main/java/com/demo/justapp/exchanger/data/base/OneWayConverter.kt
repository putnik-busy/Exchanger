package com.demo.justapp.exchanger.data.base

import android.support.annotation.NonNull

/**
 * Однонаправленный конвертор из сущности {@link F} в сущность {@link T}.
 *
 * @param <F> исходная сущность
 * @param <T> конечная сущность
 */
interface OneWayConverter<F, T> {

    @NonNull
    fun convert(from: F): T
}