package ru.mobile.lukslol.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.setEndCancelListener(listener: () -> Unit): ViewPropertyAnimator {
    setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            listener()
        }

        override fun onAnimationCancel(animation: Animator?) {
            listener()
        }
    })
    return this
}