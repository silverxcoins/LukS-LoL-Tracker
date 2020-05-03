package ru.mobile.lukslol.view

import android.os.Bundle
import android.view.View

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
}