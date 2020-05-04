package ru.mobile.lukslol.view.tape

import com.airbnb.epoxy.EpoxyController

class TapeController : EpoxyController() {

    init {
        requestModelBuild()
    }

    override fun buildModels() {
        tapeHelloView {
            id("hello")
        }
    }
}