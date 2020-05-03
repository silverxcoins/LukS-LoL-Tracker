package ru.mobile.lukslol.view.start.regionselector

import android.content.Context
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ru.mobile.lukslol.R
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.util.view.setRippleBackground

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class RegionView(context: Context) : AppCompatTextView(ContextThemeWrapper(context, R.style.TextRoboto_Body_White)) {

    init {
        val padding = resources.getDimensionPixelOffset(R.dimen.default_padding)
        setPadding(padding, padding, padding, padding)
        setRippleBackground()
    }

    @ModelProp
    fun region(region: Region) {
        text = resources.getString(R.string.enter_summoner_region_string, region.code, region.regionName)
    }

    @CallbackProp
    fun clickListener(listener: (() -> Unit)?) {
        setOnClickListener { listener?.invoke() }
    }
}