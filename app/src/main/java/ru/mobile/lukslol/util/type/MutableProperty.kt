package ru.mobile.lukslol.util.type

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class MutableProperty<T>(private val initialValue: T) {
    private val subject = BehaviorSubject.create<T>().apply { initialValue?.also(::onNext) }

    fun set(t: T) {
        subject.onNext(t)
    }

    fun get(): T {
        return subject.value!!
    }

    fun subscribe(onNext: (T) -> Unit): Disposable {
        return subject.subscribeOn(AndroidSchedulers.mainThread())
            .distinctUntilChanged()
            .subscribe(
                onNext,
                { /* TODO safeThrow */ }
            )
    }
}