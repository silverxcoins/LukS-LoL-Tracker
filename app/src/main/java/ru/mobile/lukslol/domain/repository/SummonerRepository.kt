package ru.mobile.lukslol.domain.repository

import io.reactivex.Single
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner

interface SummonerRepository {

    fun getSummoner(serviceType: ServiceType, region: Region, name: String): Single<Summoner>
}