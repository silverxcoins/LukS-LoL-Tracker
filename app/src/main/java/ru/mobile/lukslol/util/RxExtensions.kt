package ru.mobile.lukslol.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable): Disposable {
    compositeDisposable.add(this)
    return this
}