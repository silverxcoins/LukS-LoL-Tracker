package ru.mobile.lukslol.di.module

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.service.prefs.Prefs
import javax.inject.Singleton

@Module
class PreferencesModule(private val prefs: Prefs) {

    @Singleton
    @Provides
    fun providePrefs() = prefs
}