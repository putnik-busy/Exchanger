package com.demo.justapp.exchanger.data.base;

import android.support.annotation.NonNull;

/**
 * Однонаправленный конвертер из сущности {@link F} в сущность {@link T}.
 *
 * @param <F> из
 * @param <T> в
 * @author Sergey Rodionov
 */
public interface OneWayConverter<F, T> {

    /**
     * {@link F} -> {@link T}
     *
     * @param from from
     * @return to
     */
    @NonNull
    T convert(@NonNull F from);

}
