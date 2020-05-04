package ru.mobile.lukslol.di.module

import android.content.Context
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.mobile.lukslol.service.prefs.Prefs
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun providePrefs(context: Context) = Prefs(PreferenceManager.getDefaultSharedPreferences(context))
}