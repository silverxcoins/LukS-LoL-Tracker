package ru.mobile.lukslol.view.screenresult

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.mobile.lukslol.util.type.ChannelFlow
import kotlin.reflect.KClass

class ScreenResultProvider {

    private val results = ChannelFlow<ScreenResult>()

    fun newResult(result: ScreenResult) {
        GlobalScope.launch {
            results.send(result)
        }
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun <T : ScreenResult> collectResults(clazz: KClass<T>, action: (T) -> Unit) {
        return results.flow()
            .filter { result -> clazz.isInstance(result) }
            .map { result -> result as T }
            .collect { result ->  action(result) }
    }
}