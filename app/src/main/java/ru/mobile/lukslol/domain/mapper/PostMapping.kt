package ru.mobile.lukslol.domain.mapper

import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.service.db.entity.DbPost
import ru.mobile.lukslol.service.db.entity.DbPostData
import ru.mobile.lukslol.service.network.dto.NetworkPost
import ru.mobile.lukslol.service.network.dto.NetworkPost.*
import ru.mobile.lukslol.util.DateFormat
import ru.mobile.lukslol.util.parse

fun NetworkPost.toDomainPost(): Post? {
    val date = date.parse(DateFormat.SERVER)
    val summoner = summoner?.toDomainSummoner()
    return when (data) {
        is PostData.Custom -> Post.Custom(id, puuid, title, date, summoner, data.content)
        is PostData.Greeting -> Post.Greeting(id, puuid, title, date, summoner, data.message)
        else -> null
    }
}

fun DbPost.toDomainPost(): Post? {
    val summoner = summoner?.toDomainSummoner()
    return when (data) {
        is DbPostData.Custom -> Post.Custom(id, puuid, title, date, summoner, data.content)
        is DbPostData.Greeting -> Post.Greeting(id, puuid, title, date, summoner, data.message)
        else -> null
    }
}

fun Post.toDb(): DbPost {
    return DbPost(
        id,
        puuid,
        title,
        date,
        summoner?.toDbSummoner(),
        when (this) {
            is Post.Custom -> DbPostData.Custom(content = content)
            is Post.Greeting -> DbPostData.Greeting(message = message)
        }
    )
}