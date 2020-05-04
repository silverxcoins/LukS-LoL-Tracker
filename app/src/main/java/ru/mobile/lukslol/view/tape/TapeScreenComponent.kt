package ru.mobile.lukslol.view.tape

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.mobile.lukslol.di.component.AppComponent
import ru.mobile.lukslol.di.scope.ScreenScope

@ScreenScope
@Component(dependencies = [AppComponent::class])
interface TapeScreenComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun viewModel(vm: TapeViewModel): Builder

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): TapeScreenComponent
    }

    fun inject(screen: TapeScreen)
}