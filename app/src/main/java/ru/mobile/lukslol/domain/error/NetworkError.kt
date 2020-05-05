package ru.mobile.lukslol.domain.error

sealed class NetworkError : Exception() {
    object NOT_FOUND : NetworkError()
    object CONNECTION : NetworkError()
    data class UNHADLED(val e: Exception) : NetworkError()
}