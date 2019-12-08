package com.demo.justapp.exchanger.presentation.ui.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.demo.justapp.exchanger.di.application.ComponentHelper

fun <T> Fragment.getComponent(componentType: Class<T>): T {
    return ComponentHelper.getComponent(requireActivity(), componentType)
}

fun Fragment.setAsActionBar(toolbar: Toolbar) {
    (requireActivity() as? AppCompatActivity)?.setSupportActionBar(toolbar)
}