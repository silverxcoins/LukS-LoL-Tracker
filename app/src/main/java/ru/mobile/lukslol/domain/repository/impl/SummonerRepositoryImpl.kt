package ru.mobile.lukslol.domain.repository.impl

import io.reactivex.Single
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.mapper.toDomainSummoner
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs

class SummonerRepositoryImpl(private val networkManager: NetworkManager,
                             private val prefs: Prefs) : SummonerRepository {

    override fun getSummoner(serviceType: ServiceType, region: Region, name: String): Single<Summoner> {
        return when (serviceType) {
            ServiceType.NETWORK -> {
                Single.just("token")
                    .flatMap { token -> networkManager.getSummoner(name, region.code) }
                    .doOnSuccess { response -> prefs.token.set(response.token) }
                    .map { response -> response.summoner.toDomainSummoner() }
            }
            else -> throw NotImplementedError()
        }
    }
}