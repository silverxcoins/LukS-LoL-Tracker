package ru.mobile.lukslol.view.pagination

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import ru.mobile.lukslol.R

@ModelView(defaultLayout = R.layout.view_pagination_error)
class PaginationErrorView : AppCompatTextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @CallbackProp
    fun onClick(listener: (() -> Unit)?) {
        setOnClickListener { listener?.invoke() }
    }
}