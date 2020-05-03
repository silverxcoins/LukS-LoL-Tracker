package ru.mobile.lukslol.util.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addTextChangedListener(afterTextChanged: ((String) -> Unit)? = null,
                                    beforeTextChanged: ((String) -> Unit)? = null,
                                    onTextChanged: ((String) -> Unit)? = null): TextWatcher {
    return object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            afterTextChanged?.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChanged?.invoke(s.toString())
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(s.toString())
        }

    }.also { textWatcher ->
        addTextChangedListener(textWatcher)
    }
}