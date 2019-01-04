package com.demo.justapp.exchanger.exts

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * @author Sergey Rodionov
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}