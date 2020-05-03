package ru.mobile.lukslol.view.tape

import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.view.BaseViewModel

class TapeViewModel : BaseViewModel<Any, Any>() {

    init {
        Components.tapeScreenComponent.create(this)
    }

    override fun update(mutation: Any) {

    }

    override fun onCleared() {
        Components.tapeScreenComponent.clear()
        super.onCleared()
    }
}