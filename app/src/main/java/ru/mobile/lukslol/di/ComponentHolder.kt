package ru.mobile.lukslol.di

class ComponentHolder<Component, ComponentInitializer>(
    private val createComponent: (initializer: ComponentInitializer) -> Component
) {
    private var field: Component? = null

    fun get() = field

    fun create(initializer: ComponentInitializer) {
        field = createComponent(initializer)
    }

    fun clear() {
        field = null
    }
}

object EmptyInitializer