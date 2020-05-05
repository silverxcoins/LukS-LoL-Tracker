package ru.mobile.lukslol.util.type

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flow

class ChannelFlow<T> {
    private val channel = Channel<T>()
    private val internalFlow = flow {
        for (t in channel) emit(t)
    }

    suspend fun send(t: T) = channel.send(t)

    fun flow() = internalFlow
}