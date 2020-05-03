package ru.mobile.lukslol.view.start.regionselector

import com.airbnb.epoxy.EpoxyController
import ru.mobile.lukslol.domain.dto.Region

class RegionSelectorController(private val regionClickListener: (Region) -> Unit) : EpoxyController() {

    init {
        requestModelBuild()
    }

    override fun buildModels() {
        Region.values().forEach { region ->
            regionView {
                id(region.code)
                region(region)
                clickListener { regionClickListener(region) }
            }
        }
    }
}