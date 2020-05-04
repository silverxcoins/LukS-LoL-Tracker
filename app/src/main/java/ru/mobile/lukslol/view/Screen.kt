package ru.mobile.lukslol.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlin.reflect.KClass

abstract class Screen : BaseFragment(), BackPressedListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).addBackPressedListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (activity as MainActivity).removeBackPressedListener(this)
    }

    override fun onBackPressed() = false

    protected fun <T : BaseViewModel<*, *>> initViewModel(vmClass: KClass<T>) = ViewModelProviders.of(this).get(vmClass.java)
}