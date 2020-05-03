package ru.mobile.lukslol.di.component

import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Component
import ru.mobile.lukslol.di.module.NetworkModule
import ru.mobile.lukslol.di.module.PreferencesModule
import ru.mobile.lukslol.di.module.ScreenHelpersModule
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, PreferencesModule::class, ScreenHelpersModule::class])
interface AppComponent {

    fun networkManager(): NetworkManager

    fun gson(): Gson

    fun prefs(): Prefs

    fun screenResultProvider(): ScreenResultProvider
}