package ru.mobile.lukslol.di.component

import android.content.Context
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import ru.mobile.lukslol.di.module.*
import ru.mobile.lukslol.service.db.Database
import ru.mobile.lukslol.service.network.NetworkManager
import ru.mobile.lukslol.service.prefs.Prefs
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerViewModel
import ru.mobile.lukslol.view.tape.TapeViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DbModule::class, PreferencesModule::class, ScreenHelpersModule::class,
    RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun networkManager(): NetworkManager

    fun gson(): Gson

    fun db(): Database

    fun prefs(): Prefs

    fun screenResultProvider(): ScreenResultProvider

    fun inject(vm: TapeViewModel)

    fun inject(vm: EnterSummonerViewModel)
}