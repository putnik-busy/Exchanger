package com.demo.justapp.exchanger.utils;

import android.content.Context;

import com.demo.justapp.exchanger.di.application.HasComponent;

/**
 * @author Sergey Rodionov
 */
public final class ComponentUtils {

    private ComponentUtils() {
        throw new UnsupportedOperationException("can't create instance");
    }

    @SuppressWarnings("unchecked")
    public static <C> C getComponent(Context context, Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) context).getComponent());
    }
}
