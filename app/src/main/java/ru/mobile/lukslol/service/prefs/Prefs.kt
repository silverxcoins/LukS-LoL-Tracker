package ru.mobile.lukslol.service.prefs

import android.content.SharedPreferences

class Prefs(private val prefs: SharedPreferences) {

    val token = StringPref(prefs, "token")

    val summonerId = StringPref(prefs, "summoner_id")
}