package com.demo.justapp.exchanger.presentation.resources;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Менеджер для доступа к ресурсам.
 *
 * @author Sergey Rodionov
 */
public class ResourceManager {

    private final Context mContext;

    /**
     * Конструктор для {@link ResourceManager}
     *
     * @param context контекст для доступа к ресурсам
     */
    public ResourceManager(@NonNull Context context) {
        mContext = context;
    }

    /**
     * Возвращает строку из ресурсов
     *
     * @param resId id ресурса
     * @return строку из ресурсов
     */
    public String getString(@StringRes int resId) {
        return mContext.getString(resId);
    }
}
