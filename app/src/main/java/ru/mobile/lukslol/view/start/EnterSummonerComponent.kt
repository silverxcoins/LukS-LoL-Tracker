package ru.mobile.lukslol.view.start

import dagger.BindsInstance
import dagger.Component
import ru.mobile.lukslol.di.component.AppComponent
import ru.mobile.lukslol.di.scope.ScreenScope
import ru.mobile.lukslol.view.start.regionselector.RegionSelectorDialog

@ScreenScope
@Component(dependencies = [AppComponent::class])
interface EnterSummonerComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun viewModel(vm: EnterSummonerViewModel): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): EnterSummonerComponent
    }

    fun inject(screen: EnterSummonerScreen)

    fun inject(fragment: ChooseRegionFragment)

    fun inject(fragment: EnterSummonerNameFragment)

    fun inject(dialog: RegionSelectorDialog)
}