package ru.mobile.lukslol.domain.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.domain.mapper.toDb
import ru.mobile.lukslol.domain.mapper.toDomainPost
import ru.mobile.lukslol.domain.repository.FeedRepository
import ru.mobile.lukslol.service.db.Database
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import ru.mobile.lukslol.util.coroutines.launchInDb
import ru.mobile.lukslol.util.coroutines.networkRequest

class FeedRepositoryImpl(
    private val networkManager: NetworkManager,
    private val db: Database,
    private val prefs: Prefs
) : FeedRepository {

    override suspend fun getPosts(service: ServiceType, limit: Int, offset: Int, resetInDb: Boolean): List<Post> {
        val summonerId = prefs.summonerId.get()
        return when (service) {
            ServiceType.NETWORK -> networkRequest {
                val posts = networkManager.getPosts(limit, offset).await()
                    .map { post -> post.toDomainPost() }
                savePostsToDb(posts, summonerId, resetInDb)
                posts
            }
            ServiceType.DB -> withContext(Dispatchers.IO) {
                db.postDao().getPosts(summonerId!!, limit, offset)
                    .map { post -> post.toDomainPost() }
            }
            else -> throw NotImplementedError()
        }
    }

    private fun savePostsToDb(posts: List<Post>, summonerId: String?, resetInDb: Boolean) {
        launchInDb {
            summonerId?.also { puuid ->
                db.postDao().apply {
                    if (resetInDb) deleteAll(puuid)
                    insert(posts.map { post -> post.toDb() })
                }
            }
        }
    }
}