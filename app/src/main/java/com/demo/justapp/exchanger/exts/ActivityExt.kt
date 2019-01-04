package com.demo.justapp.exchanger.exts

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

/**
 * @author Sergey Rodionov
 */
fun AppCompatActivity.addFragment(fragment: Fragment,
                                  container: Int,
                                  tag: String?) {
    supportFragmentManager.inTransaction {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        add(container, fragment, tag)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment,
                                      container: Int,
                                      tag: String?) {
    supportFragmentManager.inTransaction {
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        addToBackStack(tag)
        replace(container, fragment, tag)
    }
}
