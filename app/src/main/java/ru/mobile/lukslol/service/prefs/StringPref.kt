package ru.mobile.lukslol.service.prefs

import android.content.SharedPreferences

class StringPref(prefs: SharedPreferences, key: String, private val defaultValue: String? = null)
    : BasePreference<String, String?>(prefs, key) {

    override fun save(value: String) {
        with(prefs.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun load(): String? = prefs.getString(key, defaultValue)
}