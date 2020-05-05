package ru.mobile.lukslol.util

import com.google.gson.Gson
import retrofit2.HttpException
import ru.mobile.lukslol.domain.error.NetworkError
import java.lang.Exception

fun Exception.toNetworkError(): NetworkError {
    return when ((this as? HttpException)?.code()) {
        404 -> NetworkError.NOT_FOUND
        else -> NetworkError.UNHADLED(this)
    }
}