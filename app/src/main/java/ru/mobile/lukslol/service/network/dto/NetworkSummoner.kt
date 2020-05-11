package ru.mobile.lukslol.service.network.dto

import com.google.gson.annotations.SerializedName

data class NetworkSummoner(
    val puuid: String,
    val name: String,
    val icon: String,
    @SerializedName("summonerLevel")
    val level: Int
)