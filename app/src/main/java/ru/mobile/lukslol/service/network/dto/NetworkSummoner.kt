package ru.mobile.lukslol.service.network.dto

import com.google.gson.annotations.SerializedName

data class NetworkSummoner(
    val id: String,
    val name: String,
    val icon: String,
    @SerializedName("summonerLevel")
    val level: Int
)