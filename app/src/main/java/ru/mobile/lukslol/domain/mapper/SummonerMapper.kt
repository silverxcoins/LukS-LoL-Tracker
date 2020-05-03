package ru.mobile.lukslol.domain.mapper

import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.service.network.dto.NetworkSummoner

fun NetworkSummoner.toDomainSummoner(): Summoner {
    return Summoner(id, name, icon, level)
}