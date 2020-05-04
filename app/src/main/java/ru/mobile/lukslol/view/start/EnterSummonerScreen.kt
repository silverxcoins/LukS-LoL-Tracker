package ru.mobile.lukslol.view.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.screen_enter_summoner.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.util.addTo
import ru.mobile.lukslol.util.setEndCancelListener
import ru.mobile.lukslol.util.view.show
import ru.mobile.lukslol.view.Screen
import ru.mobile.lukslol.view.start.EnterSummonerAction.Finish
import ru.mobile.lukslol.view.start.EnterSummonerAction.MoveForward
import ru.mobile.lukslol.view.start.EnterSummonerMutation.BackPressed
import ru.mobile.lukslol.view.start.EnterSummonerMutation.GoClick
import javax.inject.Inject

class EnterSummonerScreen : Screen() {

    companion object {
        private const val ANIMATION_DURATION = 300L
    }

    @Inject
    lateinit var viewModel: EnterSummonerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel(EnterSummonerViewModel::class)
        Components.enterSummonerComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_enter_summoner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    override fun onBackPressed(): Boolean {
        return if (navController().currentDestination?.id == R.id.chooseRegionFragment) {
            activity?.finish()
            true
        } else {
            viewModel.mutate(BackPressed)
            false
        }
    }

    private fun initViews() {
        enter_summoner_go_btn.setOnClickListener {
            val currentDestination = EnterSummonerDestination.fromId(navController().currentDestination!!.id)
            viewModel.mutate(GoClick(currentDestination!!))
        }
    }

    private fun initViewModel() {
        viewModel.actions { action ->
            when (action) {
                MoveForward -> navController().navigate(R.id.action_chooseRegionFragment_to_enterSummonerNameFragment)
                Finish -> startFinishAnimation()
            }
        }.addTo(disposable)
        viewModel.loading.subscribe { loading ->
            enter_summoner_loading.show(loading)
        }.addTo(disposable)
    }

    private fun navController() = Navigation.findNavController(enter_summoner_navigation_fragment)

    private fun startFinishAnimation() {
        enter_summoner_image.animate()
            .translationY(-enter_summoner_image.height.toFloat())
            .alpha(0f)
            .setDuration(ANIMATION_DURATION)
            .start()
        enter_summoner_content.animate()
            .translationY(enter_summoner_content.height.toFloat())
            .alpha(0f)
            .setDuration(ANIMATION_DURATION)
            .setEndCancelListener { topNavController.popBackStack() }
            .start()
    }
}