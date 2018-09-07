package com.demo.justapp.exchanger.presentation.resources

import android.content.Context
import android.support.annotation.StringRes

/**
 * Менеджер для доступа к ресурсам.
 *
 * @author Sergey Rodionov
 */
class ResourceManager constructor(context: Context) {

    private val mContext: Context = context

    /**
     * @param resIdRes id ресурса
     * @return строку из ресурсов
     *
     */
    fun getString(@StringRes resIdRes: Int): String = mContext.getString(resIdRes)
}