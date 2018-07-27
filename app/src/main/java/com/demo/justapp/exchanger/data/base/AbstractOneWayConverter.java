package com.demo.justapp.exchanger.data.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.functions.Function;

/**
 * Базовая реализация {@link OneWayConverter}
 *
 * @author Sergey Rodionov
 */
public abstract class AbstractOneWayConverter<F, T> implements OneWayConverter<F, T> {

    /**
     * {@inheritDoc}
     */
    @NonNull
    @Override
    public T convert(@NonNull F from) {
        throw new UnsupportedOperationException();
    }

    /**
     * Конвертирует {@link List<F>} в {@link List<T>}.
     * <p>
     * Если исходный список == {@code null} или пустой то возвращает {@link Collections#emptyList()}.
     *
     * @param fromList исходный список
     * @return результирующий список
     */
    @NonNull
    public List<T> convertList(@Nullable List<F> fromList) throws Exception {
        return iterate(fromList, this::convert);
    }

    private static <T, F> List<F> iterate(@Nullable List<T> source, Function<T, F> f) throws Exception {

        if (source == null || source.isEmpty()) {
            return Collections.emptyList();
        }

        List<F> result = new ArrayList<>();

        for (T item : source) {
            result.add(f.apply(item));
        }

        return result;
    }

}
