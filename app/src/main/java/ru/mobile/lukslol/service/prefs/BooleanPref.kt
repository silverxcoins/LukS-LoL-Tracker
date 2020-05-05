package ru.mobile.lukslol.service.prefs

import android.content.SharedPreferences

class BooleanPref(prefs: SharedPreferences, key: String, private val defaultValue: Boolean = false)
    : BasePreference<Boolean, Boolean>(prefs, key) {

    override fun save(value: Boolean) {
        with(prefs.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    override fun load(): Boolean = prefs.getBoolean(key, defaultValue)
}