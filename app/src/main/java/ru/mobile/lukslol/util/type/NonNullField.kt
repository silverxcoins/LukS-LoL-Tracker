package ru.mobile.lukslol.util.type

import androidx.databinding.ObservableField

class NonNullField<T>(initial: T) : ObservableField<T>(initial) {
    override fun get(): T {
        return super.get()!!
    }
}