package ru.mobile.lukslol.util

import android.util.Log

object Log {

    private const val DEBUG_TAG = "debugLog"

    fun d(tag: String, message: String?) {
        Log.d(tag, message ?: "null")
    }

    fun d(message: String?) {
        d(DEBUG_TAG, message)
    }
}