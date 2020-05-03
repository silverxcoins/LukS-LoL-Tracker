package ru.mobile.lukslol.util

import ru.mobile.lukslol.BuildConfig

object Environment {

    fun isDevBuild() = BuildConfig.DEBUG

    fun getHost() = "https://lt-gateway.herokuapp.com"
}