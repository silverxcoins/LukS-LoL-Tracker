package ru.mobile.lukslol.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import ru.mobile.lukslol.R

abstract class BaseFragment : Fragment(), CoroutineScope by MainScope() {

    protected lateinit var disposable: CompositeDisposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable = CompositeDisposable()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        disposable.dispose()
        cancel()
    }

    protected val mainNavController: NavController
        get() = Navigation.findNavController(requireActivity(), R.id.main_navigation_fragment)

    protected val topNavController: NavController
        get() = Navigation.findNavController(requireActivity(), R.id.top_navigation_fragment)

    protected fun <T : ViewDataBinding> inflateBinding(inflater: LayoutInflater, @LayoutRes layoutId: Int, container: ViewGroup?): T {
        return DataBindingUtil.inflate(inflater, layoutId, container, false)
    }

}