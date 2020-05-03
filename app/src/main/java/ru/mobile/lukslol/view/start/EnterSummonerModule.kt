package ru.mobile.lukslol.view.start

import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.di.scope.ScreenScope

@Module
class EnterSummonerModule(private val viewModel: EnterSummonerViewModel) {

    @Provides
    @ScreenScope
    fun provideViewModel(): EnterSummonerViewModel = viewModel
}