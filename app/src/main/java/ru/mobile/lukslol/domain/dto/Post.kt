package ru.mobile.lukslol.domain.dto

import java.util.*

sealed class Post {
    abstract val id: String
    abstract val puuid: String
    abstract val title: String
    abstract val summoner: Summoner?
    abstract val date: Calendar

    data class Custom(
        override val id: String,
        override val puuid: String,
        override val title: String,
        override val date: Calendar,
        override val summoner: Summoner?,
        val content: String
    ) : Post()

    data class Greeting(
        override val id: String,
        override val puuid: String,
        override val title: String,
        override val date: Calendar,
        override val summoner: Summoner?,
        val message: String
    ) : Post()
}