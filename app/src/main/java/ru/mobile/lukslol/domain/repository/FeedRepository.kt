package ru.mobile.lukslol.domain.repository

import ru.mobile.lukslol.domain.dto.Post

interface FeedRepository {

    suspend fun getPosts(limit: Int, offset: Int): List<Post>
}