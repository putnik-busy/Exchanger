package com.demo.justapp.exchanger.presentation.ui.base

import com.demo.justapp.exchanger.di.application.ComponentHelper
import moxy.MvpAppCompatFragment

/**
 * Базовый фрагмент для приложения.
 */
open class BaseFragment : MvpAppCompatFragment() {

    protected fun <C> getComponent(componentType: Class<C>): C {
        return ComponentHelper.getComponent(requireActivity(), componentType)
    }

}