package com.demo.justapp.exchanger.presentation.ui.extensions

import androidx.fragment.app.Fragment
import com.demo.justapp.exchanger.di.application.ComponentHelper

fun <T> Fragment.getComponent(componentType: Class<T>): T {
    return ComponentHelper.getComponent(requireActivity(), componentType)
}