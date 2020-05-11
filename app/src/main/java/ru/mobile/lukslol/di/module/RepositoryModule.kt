package ru.mobile.lukslol.di.module

import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.domain.repository.FeedRepository
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.domain.repository.impl.FeedRepositoryImpl
import ru.mobile.lukslol.domain.repository.impl.SummonerRepositoryImpl
import ru.mobile.lukslol.service.db.Database
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSummonerRepository(networkManager: NetworkManager, db: Database, prefs: Prefs): SummonerRepository {
        return SummonerRepositoryImpl(networkManager, db, prefs)
    }

    @Singleton
    @Provides
    fun provideFeedRepository(networkManager: NetworkManager, db: Database, prefs: Prefs): FeedRepository {
        return FeedRepositoryImpl(networkManager, db, prefs)
    }
}