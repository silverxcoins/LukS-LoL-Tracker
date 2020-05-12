package ru.mobile.lukslol.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.mobile.lukslol.R

class ColoredSwipeRefreshLayout : SwipeRefreshLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    init {
        setColorSchemeResources(R.color.refresh_blue, R.color.refresh_red, R.color.refresh_yellow, R.color.refresh_purple)
    }
}