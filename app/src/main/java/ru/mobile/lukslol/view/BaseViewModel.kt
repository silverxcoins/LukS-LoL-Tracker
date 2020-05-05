package ru.mobile.lukslol.view

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

abstract class BaseViewModel<Mutation, Action> : ViewModel(), CoroutineScope by MainScope() {

    private val mutations = Channel<Mutation>()
    private val actions = Channel<Action>()
    protected val compositeDisposable = CompositeDisposable()

    init {
        receiveMutations()
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()
        cancel()
    }

    fun mutate(mutation: Mutation) {
        launch {
            mutations.send(mutation)
        }
    }

    suspend fun actions(onAction: (Action) -> Unit) {
        for (action in actions) onAction(action)
    }

    protected fun action(action: Action) {
        launch(Dispatchers.Main) {
            actions.send(action)
        }
    }

    protected abstract fun update(mutation: Mutation)

    private fun receiveMutations() {
        launch(Dispatchers.Main) {
            for (mutation in mutations) update(mutation)
        }
    }
}