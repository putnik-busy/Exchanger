package com.demo.justapp.exchanger.utils

import android.content.Context
import com.demo.justapp.exchanger.di.application.HasComponent

/**
 * @author Sergey Rodionov
 */
object ComponentUtils {

    fun <C> getComponent(context: Context, componentType: Class<C>): C {
        return componentType.cast((context as HasComponent<*>).getComponent())
    }
}