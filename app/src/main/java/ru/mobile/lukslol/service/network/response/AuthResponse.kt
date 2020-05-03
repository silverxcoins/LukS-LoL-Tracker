package ru.mobile.lukslol.service.network.response

import ru.mobile.lukslol.service.network.dto.NetworkSummoner

data class AuthResponse(
    val token: String,
    val summoner: NetworkSummoner
)