package ru.mobile.lukslol.util.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mobile.lukslol.util.toNetworkError

suspend fun <T> networkRequest(action: suspend () -> T) = withContext(Dispatchers.IO) {
    try {
        action()
    } catch (e: Exception) {
        throw e.toNetworkError()
    }
}