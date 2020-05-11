package ru.mobile.lukslol.domain.mapper

import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.service.db.entity.DbSummoner
import ru.mobile.lukslol.service.network.dto.NetworkSummoner

fun NetworkSummoner.toDomainSummoner(): Summoner {
    return Summoner(puuid, name, icon, level)
}

fun DbSummoner.toDomainSummoner(): Summoner {
    return Summoner(id, name, icon, level)
}

fun Summoner.toDbSummoner(): DbSummoner {
    return DbSummoner(id, name, icon, level)
}