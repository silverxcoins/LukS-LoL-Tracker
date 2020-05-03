package ru.mobile.lukslol.view.start

import android.util.Log
import androidx.annotation.CallSuper
import dagger.Component
import io.reactivex.disposables.Disposable
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Region
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.util.addTo
import ru.mobile.lukslol.util.type.MutableProperty
import ru.mobile.lukslol.util.type.NullableMutableProperty
import ru.mobile.lukslol.view.BaseViewModel
import ru.mobile.lukslol.view.screenresult.ScreenResult
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerAction.*
import ru.mobile.lukslol.view.start.EnterSummonerDestination.*
import ru.mobile.lukslol.view.start.EnterSummonerMutation.*
import javax.inject.Inject

class EnterSummonerViewModel : BaseViewModel<EnterSummonerMutation, EnterSummonerAction>() {

    @Inject
    lateinit var summonerRepository: SummonerRepository
    @Inject
    lateinit var screenResultProvider: ScreenResultProvider

    init {
        Components.summonerComponent.get()!!.inject(this)
        Components.enterSummonerComponent.create(this)
    }

    val region = MutableProperty(Region.RU)
    val input = MutableProperty("")
    val loading = MutableProperty(false)

    private val summoner = NullableMutableProperty<Summoner>()

    private var summonerLoadingDisposable: Disposable? = null

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
                summonerLoadingDisposable?.dispose()
            }
            is SummonerLoaded -> {
                loading.set(false)
                summoner.set(mutation.summoner)
                action(Finish)
            }
            is SummonerLoadFailed -> {
                loading.set(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Components.enterSummonerComponent.clear()
        summoner.get()?.also { screenResultProvider.newResult(EnterSummonerScreenResult(it)) }
    }

    private fun loadSummoner() {
        summonerLoadingDisposable = summonerRepository.getSummoner(ServiceType.NETWORK, region.get(), input.get())
            .subscribe(
                { summoner -> mutate(SummonerLoaded(summoner)) },
                { mutate(SummonerLoadFailed) }
            ).addTo(compositeDisposable)
    }
}

class EnterSummonerScreenResult(val summoner: Summoner) : ScreenResult