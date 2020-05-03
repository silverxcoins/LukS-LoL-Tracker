package ru.mobile.lukslol.service.prefs

import android.content.SharedPreferences

abstract class BasePreference<T, D: T?>(protected val prefs: SharedPreferences, protected val key: String) {

    protected abstract fun save(value: T)

    protected abstract fun load(): D

    fun set(value: T) {
        save(value)
    }

    fun get(): D {
        return load()
    }

    fun clear() {
        prefs.edit().remove(key).apply()
    }
}