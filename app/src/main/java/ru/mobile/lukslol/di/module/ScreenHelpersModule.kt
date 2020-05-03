package ru.mobile.lukslol.di.module

import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import javax.inject.Singleton

@Module
class ScreenHelpersModule {

    @Singleton
    @Provides
    fun provideScreenResultProvider() = ScreenResultProvider()
}