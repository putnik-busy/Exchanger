package com.demo.justapp.exchanger.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * @author Sergey Rodionov
 */
public final class ActivityUtils {

    private ActivityUtils() {
        throw new UnsupportedOperationException("can't create instance");
    }

    public static void addFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment,
                                             int container,
                                             String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(container, fragment, tag).commit();
    }

    public static void replaceFragmentInActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment,
                                                 int container,
                                                 String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(tag);
        transaction.replace(container, fragment, tag).commit();
    }

}
