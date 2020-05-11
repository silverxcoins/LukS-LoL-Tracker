package ru.mobile.lukslol.domain.repository.impl

import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.domain.mapper.toDomainPost
import ru.mobile.lukslol.domain.repository.FeedRepository
import ru.mobile.lukslol.service.db.Database
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import ru.mobile.lukslol.util.coroutines.networkRequest

class FeedRepositoryImpl(
    private val networkManager: NetworkManager,
    private val db: Database,
    private val prefs: Prefs
) : FeedRepository {

    override suspend fun getPosts(limit: Int, offset: Int): List<Post> {
        return networkRequest {
            networkManager.getPosts(limit, offset).await()
                .mapNotNull { post -> post.toDomainPost() }
        }
    }
}