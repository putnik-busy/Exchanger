package com.demo.justapp.exchanger.di.application

import android.content.Context

object ComponentHelper {

    fun <T> getComponent(context: Context, componentType: Class<T>): T {
        return componentType.cast((context as HasComponent<*>).getComponent())!!
    }

}