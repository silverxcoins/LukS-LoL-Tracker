package ru.mobile.lukslol.view.tape

import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.di.scope.ScreenScope

@Module
class TapeScreenModule(private val tapeViewModel: TapeViewModel) {

    @ScreenScope
    @Provides
    fun provideViewModel() = tapeViewModel
}