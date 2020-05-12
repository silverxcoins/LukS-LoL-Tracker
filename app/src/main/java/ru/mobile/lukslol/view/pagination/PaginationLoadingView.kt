package ru.mobile.lukslol.view.pagination

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import ru.mobile.lukslol.R

@ModelView(defaultLayout = R.layout.view_pagination_loading)
class PaginationLoadingView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}