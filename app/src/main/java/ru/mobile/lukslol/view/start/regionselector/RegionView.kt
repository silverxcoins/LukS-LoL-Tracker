package ru.mobile.lukslol.view.start.regionselector

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ru.mobile.lukslol.R
import ru.mobile.lukslol.databinding.ViewRegionBinding
import ru.mobile.lukslol.domain.dto.Region

@ModelView(defaultLayout = R.layout.view_region)
class RegionView : AppCompatTextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val binding = ViewRegionBinding.bind(this).apply { eventHandler = EventHandler() }
    private var clickListener: ((Region) -> Unit)? = null

    @ModelProp
    fun region(region: Region) {x
        binding.region = region
    }

    @CallbackProp
    fun clickListener(listener: ((Region) -> Unit)?) {
        clickListener = listener
    }

    inner class EventHandler {
        fun onClick(region: Region) {
            clickListener?.invoke(region)
        }
    }
}