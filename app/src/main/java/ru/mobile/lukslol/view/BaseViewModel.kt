package ru.mobile.lukslol.view

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import ru.mobile.lukslol.util.addTo

abstract class BaseViewModel<Mutation, Action> : ViewModel() {

    private val mutations = PublishSubject.create<Mutation>()
    private val actions = PublishSubject.create<Action>()
    protected val compositeDisposable = CompositeDisposable()

    init {
        subscribeOnMutations()
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun mutate(mutation: Mutation) {
        mutations.onNext(mutation)
    }

    fun actions(action: (Action) -> Unit): Disposable {
        return actions.observeOn(AndroidSchedulers.mainThread())
            .subscribe(action)
    }

    protected fun action(action: Action) {
        actions.onNext(action)
    }

    protected abstract fun update(mutation: Mutation)

    private fun subscribeOnMutations() {
        mutations.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::update,
                { /* TODO safeThrow */ }
            ).addTo(compositeDisposable)
    }
}