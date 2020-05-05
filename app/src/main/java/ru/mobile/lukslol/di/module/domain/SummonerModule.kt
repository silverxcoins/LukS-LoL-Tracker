package ru.mobile.lukslol.di.module.domain

import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.di.scope.DomainScope
import ru.mobile.lukslol.di.scope.LogicScope
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.domain.repository.impl.SummonerRepositoryImpl
import ru.mobile.lukslol.service.db.Database
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import javax.inject.Singleton

@Module
class SummonerModule {

    @DomainScope
    @Provides
    fun provideSummonerRepository(networkManager: NetworkManager, db: Database, prefs: Prefs): SummonerRepository {
        return SummonerRepositoryImpl(networkManager, db, prefs)
    }
}