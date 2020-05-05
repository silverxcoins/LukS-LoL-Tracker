package ru.mobile.lukslol.domain.repository.impl

import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.ServiceType.NETWORK
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.mapper.toDomainSummoner
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import ru.mobile.lukslol.util.coroutines.networkRequest

class SummonerRepositoryImpl(
    private val networkManager: NetworkManager,
    private val prefs: Prefs
) : SummonerRepository {

    override suspend fun getSummoner(serviceType: ServiceType, region: Region, name: String): Summoner {
        return when (serviceType) {
            NETWORK ->
                networkRequest {
                    val response = networkManager.getSummoner(name, region.code).await()
                    prefs.token.set(response.token)
                    response.summoner.toDomainSummoner()
                }
            else -> throw NotImplementedError()
        }
    }
}