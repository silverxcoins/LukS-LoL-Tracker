package ru.mobile.lukslol.view.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mobile.lukslol.R
import ru.mobile.lukslol.databinding.FragmentChooseRegionBinding
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.view.BaseFragment
import ru.mobile.lukslol.view.start.regionselector.RegionSelectorDialog
import javax.inject.Inject

class ChooseRegionFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: EnterSummonerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Components.enterSummonerComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflateBinding<FragmentChooseRegionBinding>(inflater, R.layout.fragment_choose_region, container)
        binding.fragment = this
        binding.viewModel = viewModel
        return binding.root
    }

    fun regionClick() {
        RegionSelectorDialog().show(childFragmentManager, null)
    }
}