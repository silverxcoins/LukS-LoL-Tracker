package ru.mobile.lukslol.view.start

import androidx.databinding.ObservableField
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.util.type.NonNullField
import ru.mobile.lukslol.view.BaseViewModel
import ru.mobile.lukslol.view.screenresult.ScreenResult
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerAction.*
import ru.mobile.lukslol.view.start.EnterSummonerDestination.NAME
import ru.mobile.lukslol.view.start.EnterSummonerDestination.REGION
import ru.mobile.lukslol.view.start.EnterSummonerMutation.*
import javax.inject.Inject

class EnterSummonerViewModel : BaseViewModel<EnterSummonerMutation, EnterSummonerAction>() {

    @Inject
    lateinit var summonerRepository: SummonerRepository
    @Inject
    lateinit var screenResultProvider: ScreenResultProvider

    init {
        Components.summonerComponent.get().inject(this)
        Components.enterSummonerComponent.create(this)
    }

    val region = NonNullField(Region.RU)
    val input = NonNullField("")
    val loading = NonNullField(false)

    private val summoner = ObservableField<Summoner>()

    private var summonerLoadingJob: Job? = null

    override fun update(mutation: EnterSummonerMutation) {
        when (mutation) {
            is RegionChanged -> region.set(mutation.region)
            is InputChanged -> input.set(mutation.input)
            is GoClick -> {
                when (mutation.currentDestination) {
                    REGION -> action(MoveForward)
                    NAME -> {
                        loading.set(true)
                        loadSummoner()
                    }
                }
            }
            is BackPressed -> {
                loading.set(false)
                summonerLoadingJob?.cancel()
            }
            is SummonerLoaded -> {
                loading.set(false)
                summoner.set(mutation.summoner)
                action(Finish)
            }
            is SummonerLoadFailed -> {
                loading.set(false)
                action(ShowErrorSnack(mutation.exception))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Components.enterSummonerComponent.clear()
        summoner.get()?.also { screenResultProvider.newResult(EnterSummonerScreenResult(it)) }
    }

    private fun loadSummoner() {
        summonerLoadingJob = launch {
            try {
                val summoner = summonerRepository.getSummoner(ServiceType.NETWORK, region.get(), input.get())
                if (isActive) mutate(SummonerLoaded(summoner))
            } catch (e: Exception) {
                if (isActive) mutate(SummonerLoadFailed(e))
            }
        }
    }
}

class EnterSummonerScreenResult(val summoner: Summoner) : ScreenResult