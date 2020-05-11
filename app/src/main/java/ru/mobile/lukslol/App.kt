package ru.mobile.lukslol

import android.app.Application
import androidx.preference.PreferenceManager
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.di.EmptyInitializer
import ru.mobile.lukslol.di.component.AppComponent
import ru.mobile.lukslol.di.component.DaggerAppComponent

class App : Application() {

    companion object {

        private lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        Components.appComponent.create(this)
    }

}