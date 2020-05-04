package ru.mobile.lukslol.view.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mobile.lukslol.R
import ru.mobile.lukslol.databinding.FragmentEnterSummonerNameBinding
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.view.BaseFragment
import javax.inject.Inject

class EnterSummonerNameFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: EnterSummonerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Components.enterSummonerComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflateBinding<FragmentEnterSummonerNameBinding>(inflater, R.layout.fragment_enter_summoner_name, container)
        binding.viewModel = viewModel
        return binding.root
    }
}