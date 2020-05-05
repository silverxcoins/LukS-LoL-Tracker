package ru.mobile.lukslol.view.tape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.screen_tape.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.databinding.ScreenTapeBinding
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.view.Screen
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import javax.inject.Inject

class TapeScreen : Screen() {

    companion object {
        @BindingAdapter("tapeSummonerAvatar")
        @JvmStatic
        fun loadAvatar(view: ImageView, url: String?) {
            Glide.with(view.context)
                .load(url)
                .placeholder(R.drawable.circle_placeholder)
                .error(R.drawable.circle_placeholder)
                .transform(RoundedCorners(view.resources.getDimensionPixelSize(R.dimen.tape_avatar_size) / 2))
                .into(view)
        }
    }

    @Inject
    lateinit var viewModel: TapeViewModel
    @Inject
    lateinit var screenResultProvider: ScreenResultProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel(TapeViewModel::class)
        Components.tapeScreenComponent.get().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflateBinding<ScreenTapeBinding>(inflater, R.layout.screen_tape, container)
        binding.screen = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initList()
    }

    fun onSummonerIconClick() {
        topNavController.navigate(R.id.enterSummonerScreen)
    }

    private fun initList() {
        tape_list.apply {
            layoutManager = LinearLayoutManager(context)
            setController(TapeController())
        }
    }
}