package ru.mobile.lukslol.di

import android.content.Context
import ru.mobile.lukslol.di.component.AppComponent
import ru.mobile.lukslol.di.component.DaggerAppComponent
import ru.mobile.lukslol.di.component.domain.DaggerSummonerComponent
import ru.mobile.lukslol.di.component.domain.SummonerComponent
import ru.mobile.lukslol.view.start.DaggerEnterSummonerComponent
import ru.mobile.lukslol.view.start.EnterSummonerComponent
import ru.mobile.lukslol.view.start.EnterSummonerViewModel
import ru.mobile.lukslol.view.tape.DaggerTapeScreenComponent
import ru.mobile.lukslol.view.tape.TapeScreenComponent
import ru.mobile.lukslol.view.tape.TapeViewModel

object Components {

    val appComponent = ComponentHolder<AppComponent, Context> { context ->
        DaggerAppComponent.builder()
            .context(context)
            .build()
    }

    val summonerComponent = ComponentHolder<SummonerComponent, EmptyInitializer> {
        DaggerSummonerComponent.builder().appComponent(appComponent.get()).build()
    }

    val enterSummonerComponent = ComponentHolder<EnterSummonerComponent, EnterSummonerViewModel> { vm ->
        DaggerEnterSummonerComponent.builder()
            .viewModel(vm)
            .appComponent(appComponent.get())
            .build()
    }

    val tapeScreenComponent = ComponentHolder<TapeScreenComponent, TapeViewModel> { vm ->
        DaggerTapeScreenComponent.builder()
            .viewModel(vm)
            .appComponent(appComponent.get())
            .build()
    }

}