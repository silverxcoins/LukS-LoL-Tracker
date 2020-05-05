package ru.mobile.lukslol.view.tape

import androidx.databinding.ObservableField
import kotlinx.coroutines.launch
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.view.BaseViewModel
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerScreenResult
import ru.mobile.lukslol.view.tape.TapeAction.ShowEnterSummonerScreen
import ru.mobile.lukslol.view.tape.TapeMutation.*
import javax.inject.Inject

class TapeViewModel : BaseViewModel<TapeMutation, TapeAction>() {

    @Inject
    lateinit var screenResultProvider: ScreenResultProvider
    @Inject
    lateinit var summonerRepository: SummonerRepository

    init {
        Components.tapeScreenComponent.create(this)
        Components.summonerComponent.get().inject(this)

        loadSummoner()
        collectSummonerFromEnterScreen()
    }

    val summoner = ObservableField<Summoner>()

    override fun update(mutation: TapeMutation) {
        when (mutation) {
            is SummonerReceived -> summoner.set(mutation.summoner)
            is NoSummonerInDb, SummonerIconClick -> action(ShowEnterSummonerScreen)
        }
    }

    override fun onCleared() {
        Components.tapeScreenComponent.clear()
        super.onCleared()
    }

    private fun loadSummoner() {
        launch {
            try {
                summonerRepository.getCurrentSummoner(ServiceType.DB)
                    ?.also { summoner -> mutate(SummonerReceived(summoner)) }
                    ?: mutate(NoSummonerInDb)
            } catch (e: Exception) {
                mutate(NoSummonerInDb)
            }
        }
    }

    private fun collectSummonerFromEnterScreen() {
        launch {
            screenResultProvider.collectResults<EnterSummonerScreenResult> { result ->
                mutate(SummonerReceived(result.summoner))
            }
        }
    }
}