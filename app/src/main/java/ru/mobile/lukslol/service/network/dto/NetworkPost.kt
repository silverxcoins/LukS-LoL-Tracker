package ru.mobile.lukslol.service.network.dto

import com.google.gson.annotations.SerializedName

data class NetworkPost(
    val id: String,
    val puuid: String,
    val title: String,
    val date: String,
    val type: String,
    val data: PostData
) {
    sealed class PostData {
        object Unknown : PostData()

        data class Custom(
            val content: String
        ) : PostData() {
            companion object { const val serverName = "CUSTOM" }
        }

        data class Greeting(
            val message: String,
            val summoner: NetworkSummoner
        ) : PostData() {
            companion object { const val serverName = "GREETING" }
        }
    }
}