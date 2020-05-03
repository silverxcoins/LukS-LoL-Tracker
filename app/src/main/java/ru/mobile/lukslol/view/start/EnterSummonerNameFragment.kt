package ru.mobile.lukslol.view.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_enter_summoner_name.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.util.view.addTextChangedListener
import ru.mobile.lukslol.view.BaseFragment
import ru.mobile.lukslol.view.start.EnterSummonerMutation.InputChanged
import javax.inject.Inject

class EnterSummonerNameFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: EnterSummonerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Components.enterSummonerComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_enter_summoner_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initInput()
    }

    private fun initInput() {
        enter_summoner_name_field.setText(viewModel.input.get())
        enter_summoner_name_field.addTextChangedListener { input -> viewModel.mutate(InputChanged(input)) }
    }
}