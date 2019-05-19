package com.demo.justapp.exchanger.presentation.rates.adapter.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Класс экстеншен функций для [EditText]
 *
 * @author Sergey Rodionov
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}