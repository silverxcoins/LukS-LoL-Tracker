package ru.mobile.lukslol.util.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.mobile.lukslol.R

fun View.show(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.setRippleBackground(borderless: Boolean = false) {
    val outValue = TypedValue()
    val attr = if (borderless) R.attr.selectableItemBackgroundBorderless else R.attr.selectableItemBackground
    context.theme.resolveAttribute(attr, outValue, true)
    setBackgroundResource(outValue.resourceId)
}

fun View.addOneShotLayoutChangeListener(onLayoutAction: () -> Unit) {
    addOnLayoutChangeListener(OneShotLayoutChangeListener(this, onLayoutAction))
}

fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

private class OneShotLayoutChangeListener(private val view: View, private val onLayoutAction: () -> Unit)
    : View.OnLayoutChangeListener {
    override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
        view.removeOnLayoutChangeListener(this)
        onLayoutAction.invoke()
    }
}
