package com.demo.justapp.exchanger.presentation.ui.base

import com.arellomobile.mvp.MvpAppCompatFragment
import com.demo.justapp.exchanger.di.application.ComponentHelper

/**
 * Базовый фрагмент для приложения.
 */
open class BaseFragment : MvpAppCompatFragment() {

    protected fun <C> getComponent(componentType: Class<C>): C {
        return ComponentHelper.getComponent(requireActivity(), componentType)
    }

}