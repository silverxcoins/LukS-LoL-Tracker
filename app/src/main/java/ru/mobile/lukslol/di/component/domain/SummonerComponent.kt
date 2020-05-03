package ru.mobile.lukslol.di.component.domain

import dagger.Component
import ru.mobile.lukslol.di.component.AppComponent
import ru.mobile.lukslol.di.module.domain.SummonerModule
import ru.mobile.lukslol.di.scope.DomainScope
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.view.start.EnterSummonerViewModel

@DomainScope
@Component(dependencies = [AppComponent::class], modules = [SummonerModule::class])
interface SummonerComponent {

    fun summonerRepository(): SummonerRepository

    fun inject(vm: EnterSummonerViewModel)
}