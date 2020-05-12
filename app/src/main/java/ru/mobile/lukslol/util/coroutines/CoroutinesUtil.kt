package ru.mobile.lukslol.util.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mobile.lukslol.util.ignoreErrors
import ru.mobile.lukslol.util.toNetworkError

suspend fun <T> networkRequest(action: suspend () -> T) = withContext(Dispatchers.IO) {
    try {
        action()
    } catch (e: Exception) {
        throw e.toNetworkError()
    }
}

fun launchInDb(action: () -> Unit) {
    GlobalScope.launch {
        ignoreErrors(action)
    }
}