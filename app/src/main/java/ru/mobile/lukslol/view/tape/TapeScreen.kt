package ru.mobile.lukslol.view.tape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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

        initViewModel(TapeViewModel::class)
        Components.tapeScreenComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_tape, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClicks()
        initList()
        initViewModel()
    }

    private fun initClicks() {
        tape_summoner_icon.setOnClickListener {
            topNavController.navigate(R.id.enterSummonerScreen)
        }
    }

    private fun initList() {
        tape_list.apply {
            layoutManager = LinearLayoutManager(context)
            setController(TapeController())
        }
    }

    private fun initViewModel() {
        viewModel.summoner.subscribe { summoner ->
            tape_summoner_name.text = summoner.name
            loadSummonerIcon(summoner.icon)
        }.addTo(disposable)
    }

    private fun loadSummonerIcon(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.circle_placeholder)
            .error(R.drawable.circle_placeholder)
            .transform(RoundedCorners(resources.getDimensionPixelSize(R.dimen.tape_avatar_size) / 2))
            .into(tape_summoner_icon)
    }


}