package com.demo.justapp.exchanger.presentation.ui.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


fun View.hideKeyboard() {
    val inputMethodManager = this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.updatePadding(
    left: Int = paddingLeft,
    top: Int = paddingTop,
    right: Int = paddingRight,
    bottom: Int = paddingBottom
) {
    return setPadding(left, top, right, bottom)
}

fun View.addSystemTopPadding() {
    applyWindowInsets { view, insets, initialPadding ->
        view.updatePadding(
            top = initialPadding.top + insets.systemWindowInsetTop
        )
    }
}

fun View.addSystemBottomPadding() {
    applyWindowInsets { view, insets, initialPadding ->
        view.updatePadding(
            bottom = initialPadding.bottom + insets.systemWindowInsetBottom
        )
    }
}

fun View.applyWindowInsets(block: (view: View, insets: WindowInsetsCompat, initialPadding: Rect) -> Unit) {
    val initialPadding = saveInitialPaddingForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        block(view, insets, initialPadding)
        insets
    }
    requestApplyInsetsWhenAttached()
}

private fun saveInitialPaddingForView(view: View): Rect =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit

        })
    }
}
