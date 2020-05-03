package ru.mobile.lukslol.view.tape

import dagger.Component
import ru.mobile.lukslol.di.component.AppComponent
import ru.mobile.lukslol.di.scope.ScreenScope

@ScreenScope
@Component(dependencies = [AppComponent::class], modules = [TapeScreenModule::class])
interface TapeScreenComponent {

    fun inject(screen: TapeScreen)
}