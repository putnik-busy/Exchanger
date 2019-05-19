package com.demo.justapp.exchanger.di.application

import android.content.Context

object ComponentHelper {

    fun <C> getComponent(context: Context, componentType: Class<C>): C {
        return componentType.cast((context as HasComponent<C>).getComponent())
    }

}