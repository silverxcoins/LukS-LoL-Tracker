package ru.mobile.lukslol.util.rx

import io.reactivex.Observable

inline fun <reified Target> Observable<*>.filterIsInstance(): Observable<Target> {
    return this.filter { it is Target }
        .map { it as Target }
}