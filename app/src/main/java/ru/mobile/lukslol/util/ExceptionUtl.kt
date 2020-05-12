package ru.mobile.lukslol.util

import retrofit2.HttpException
import ru.mobile.lukslol.domain.error.NetworkError
import java.lang.Exception

fun Exception.toNetworkError(): NetworkError {
    return when ((this as? HttpException)?.code()) {
        404 -> NetworkError.NOT_FOUND
        else -> NetworkError.UNHADLED(this)
    }
}

fun <T> ignoreErrors(action: () -> T) {
    try {
        action()
    } catch (e: Exception) {
    }
}

fun <T> tryOrNull(action : () -> T): T? {
    return try {
        action()
    } catch (e: Exception) {
        null
    }
}