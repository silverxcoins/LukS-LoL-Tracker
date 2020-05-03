package ru.mobile.lukslol.view

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.reactivex.disposables.CompositeDisposable
import ru.mobile.lukslol.R

abstract class BaseFragment : Fragment() {

    protected val disposable = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()

        disposable.dispose()
    }

    protected val mainNavController: NavController
        get() = Navigation.findNavController(requireActivity(), R.id.main_navigation_fragment)

    protected val topNavController: NavController
        get() = Navigation.findNavController(requireActivity(), R.id.top_navigation_fragment)

}