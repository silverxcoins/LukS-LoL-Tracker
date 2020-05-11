package ru.mobile.lukslol.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonElement

inline fun <reified T> JsonDeserializationContext.parse(json: JsonElement): T {
    return deserialize(json, T::class.java) as T
}