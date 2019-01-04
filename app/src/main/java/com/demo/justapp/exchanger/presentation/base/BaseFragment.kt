package com.demo.justapp.exchanger.presentation.base

import com.arellomobile.mvp.MvpAppCompatFragment
import com.demo.justapp.exchanger.exts.ComponentUtils

/**
 * Базовый фрагмент для приложения.
 * <p>
 * Тут будет какая-то общая логика для всех фрагментов в приложении.
 *
 * @author Sergey Rodionov
 */
open class BaseFragment : MvpAppCompatFragment() {

    protected fun <C> getComponent(componentType: Class<C>): C {
        val activity = checkNotNull(activity)
        return ComponentUtils.getComponent(activity, componentType)
    }

}