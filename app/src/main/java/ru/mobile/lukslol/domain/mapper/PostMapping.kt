package ru.mobile.lukslol.domain.mapper

import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.service.network.dto.NetworkPost
import ru.mobile.lukslol.service.network.dto.NetworkPost.*
import ru.mobile.lukslol.util.DateFormat
import ru.mobile.lukslol.util.parse

fun NetworkPost.toDomainPost(): Post? {
    val date = date.parse(DateFormat.SERVER)
    return when (data) {
        is PostData.Custom -> Post.Custom(id, puuid, title, date, data.content)
        is PostData.Greeting -> Post.Greeting(id, puuid, title, date, data.message, data.summoner.toDomainSummoner())
        else -> null
    }
}