package ru.mobile.lukslol.service.prefs

import android.content.SharedPreferences

abstract class BasePreference<T, D: T?>(protected val prefs: SharedPreferences, protected val key: String) {

    protected abstract fun save(value: T)

    protected abstract fun load(): D

    private val lock = Any()

    fun set(value: T) {
        synchronized(lock) {
            save(value)
        }
    }

    fun get() = synchronized(lock) { load() }

    fun clear() {
        synchronized(lock) {
            prefs.edit().remove(key).apply()
        }
    }
}