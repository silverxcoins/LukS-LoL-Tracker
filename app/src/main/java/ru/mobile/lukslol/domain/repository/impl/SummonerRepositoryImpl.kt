package ru.mobile.lukslol.domain.repository.impl

import kotlinx.coroutines.*
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.ServiceType.DB
import ru.mobile.lukslol.domain.ServiceType.NETWORK
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.mapper.toDbSummoner
import ru.mobile.lukslol.domain.mapper.toDomainSummoner
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.service.db.Database
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import ru.mobile.lukslol.util.coroutines.networkRequest
import ru.mobile.lukslol.util.ignoreErrors

class SummonerRepositoryImpl(
    private val networkManager: NetworkManager,
    private val db: Database,
    private val prefs: Prefs
) : SummonerRepository {

    override suspend fun getSummoner(serviceType: ServiceType, region: Region, name: String): Summoner {
        return when (serviceType) {
            NETWORK ->
                networkRequest {
                    val response = networkManager.getSummoner(name, region.code).await()
                    val summoner = response.summoner.toDomainSummoner()
                    GlobalScope.launch {
                        ignoreErrors {
                            prefs.token.set(response.token)
                            prefs.summonerId.set(summoner.id)
                        }
                        ignoreErrors {
                            db.summonerDao().insert(summoner.toDbSummoner())
                        }
                    }
                    summoner
                }
            else -> throw NotImplementedError()
        }
    }

    override suspend fun getCurrentSummoner(serviceType: ServiceType): Summoner? {
        return when (serviceType) {
            DB ->
                withContext(Dispatchers.IO) {
                    prefs.summonerId.get()
                        ?.let { id -> db.summonerDao().getById(id)?.toDomainSummoner()}
                }
            else -> throw NotImplementedError()
        }
    }
}