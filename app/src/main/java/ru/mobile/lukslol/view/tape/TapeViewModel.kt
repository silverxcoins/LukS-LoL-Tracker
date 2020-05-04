package ru.mobile.lukslol.view.tape

import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.util.addTo
import ru.mobile.lukslol.util.type.NullableMutableProperty
import ru.mobile.lukslol.view.BaseViewModel
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerScreenResult
import javax.inject.Inject

class TapeViewModel : BaseViewModel<Any, Any>() {

    @Inject
    lateinit var screenResultProvider: ScreenResultProvider

    init {
        Components.tapeScreenComponent.create(this)
        Components.summonerComponent.get()?.inject(this)

        subscribeSummoner()
    }

    val summoner = NullableMutableProperty<Summoner>()

    override fun update(mutation: Any) {
    }

    override fun onCleared() {
        Components.tapeScreenComponent.clear()
        super.onCleared()
    }

    private fun subscribeSummoner() {
        screenResultProvider.results<EnterSummonerScreenResult>()
            .subscribe { result -> summoner.set(result.summoner) }
            .addTo(compositeDisposable)
    }
}