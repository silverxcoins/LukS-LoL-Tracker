package ru.mobile.lukslol.domain.repository

import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner

interface SummonerRepository {

    suspend fun getSummoner(serviceType: ServiceType, region: Region, name: String): Summoner

    suspend fun getCurrentSummoner(serviceType: ServiceType): Summoner?
}