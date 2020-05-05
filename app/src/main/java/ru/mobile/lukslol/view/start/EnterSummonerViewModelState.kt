package ru.mobile.lukslol.view.start

import ru.mobile.lukslol.R
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner

sealed class EnterSummonerMutation {
    data class GoClick(val currentDestination: EnterSummonerDestination) : EnterSummonerMutation()
    data class InputChanged(val input: String) : EnterSummonerMutation()
    data class RegionChanged(val region: Region) : EnterSummonerMutation()
    object BackPressed : EnterSummonerMutation()
    data class SummonerLoaded(val summoner: Summoner) : EnterSummonerMutation()
    data class SummonerLoadFailed(val exception: Exception) : EnterSummonerMutation()
}

sealed class EnterSummonerAction {
    object MoveForward : EnterSummonerAction()
    object Finish : EnterSummonerAction()
    data class ShowErrorSnack(val e: Exception) : EnterSummonerAction()
}

enum class EnterSummonerDestination {
    REGION,
    NAME;

    companion object {
        fun fromId(id: Int): EnterSummonerDestination? {
            return when (id) {
                R.id.chooseRegionFragment -> REGION
                R.id.enterSummonerNameFragment -> NAME
                else -> null
            }
        }
    }
}