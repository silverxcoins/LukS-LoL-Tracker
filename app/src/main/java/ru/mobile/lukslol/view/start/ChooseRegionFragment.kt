package ru.mobile.lukslol.view.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_choose_region.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.util.addTo
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
        return inflater.inflate(R.layout.fragment_choose_region, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        choose_region_region_text.setOnClickListener {
            RegionSelectorDialog().show(childFragmentManager, null)
        }
    }

    private fun initViewModel() {
        viewModel.region.subscribe { region ->
            choose_region_region_text.text =
                resources.getString(R.string.enter_summoner_region_string, region.code, region.regionName)
        }.addTo(disposable)
    }
}