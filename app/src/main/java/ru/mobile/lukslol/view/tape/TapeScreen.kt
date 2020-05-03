package ru.mobile.lukslol.view.tape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.screen_tape.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.util.addTo
import ru.mobile.lukslol.view.Screen
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerScreenResult
import javax.inject.Inject

class TapeScreen : Screen() {

    @Inject
    lateinit var viewModel: TapeViewModel
    @Inject
    lateinit var screenResultProvider: ScreenResultProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewModelProviders.of(this).get(TapeViewModel::class.java)
        Components.tapeScreenComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_tape, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenResultProvider.results<EnterSummonerScreenResult>().subscribe { result ->
            tape_summoner_name.text = result.summoner.name
            Glide.with(this)
                .load(result.summoner.icon)
                .placeholder(R.drawable.circle_placeholder)
                .error(R.drawable.circle_placeholder)
                .transform(RoundedCorners(resources.getDimensionPixelSize(R.dimen.tape_avatar_size) / 2))
                .into(tape_summoner_icon)
        }.addTo(disposable)
    }


}