package com.demo.justapp.exchanger.presentation.base;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.demo.justapp.exchanger.utils.ComponentUtils;

import dagger.internal.Preconditions;

/**
 * @author Sergey Rodionov
 */
public class BaseFragment extends MvpAppCompatFragment {

    protected <C> C getComponent(Class<C> componentType) {
        Preconditions.checkNotNull(getActivity());
        return ComponentUtils.getComponent(getActivity(), componentType);
    }

}
