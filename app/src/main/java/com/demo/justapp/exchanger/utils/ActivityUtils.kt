package com.demo.justapp.exchanger.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * @author Sergey Rodionov
 */
class ActivityUtils private constructor() {

    init {
        throw UnsupportedOperationException("can't create instance")
    }

    companion object {

        fun addFragmentInActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment,
                                  container: Int,
                                  tag: String) {
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.add(container, fragment, tag).commit()
        }

        fun replaceFragmentInActivity(fragmentManager: FragmentManager,
                                      fragment: Fragment,
                                      container: Int,
                                      tag: String) {
            val transaction = fragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction.addToBackStack(tag)
            transaction.replace(container, fragment, tag).commit()
        }
    }

}
