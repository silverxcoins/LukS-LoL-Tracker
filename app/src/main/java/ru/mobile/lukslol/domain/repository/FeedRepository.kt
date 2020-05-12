package ru.mobile.lukslol.domain.repository

import android.database.Cursor
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Post

interface FeedRepository {

    suspend fun getPosts(service: ServiceType, limit: Int, offset: Int, resetInDb: Boolean): List<Post>
}