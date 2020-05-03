package ru.mobile.lukslol.view.screenresult

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import ru.mobile.lukslol.util.rx.filterIsInstance

class ScreenResultProvider {

    val allResults = PublishSubject.create<ScreenResult>()

    fun newResult(result: ScreenResult) {
        allResults.onNext(result)
    }

    inline fun <reified T : ScreenResult> results(): Observable<T> {
        return allResults
            .observeOn(AndroidSchedulers.mainThread())
            .filterIsInstance()
    }
}