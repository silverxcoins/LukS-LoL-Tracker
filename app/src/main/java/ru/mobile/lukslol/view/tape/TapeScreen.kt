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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.screen_tape.*
import kotlinx.coroutines.launch
import ru.mobile.lukslol.R
import ru.mobile.lukslol.databinding.ScreenTapeBinding
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.domain.error.NetworkError
import ru.mobile.lukslol.view.Screen
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.tape.TapeAction.*
import ru.mobile.lukslol.view.tape.TapeMutation.*
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

    private val controller = TapeController()

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
        initSwipeRefresh()
        initActions()
    }

    fun onSummonerIconClick() {
        viewModel.mutate(SummonerIconClick)
    }

    private fun initList() {
        tape_list.apply {
            layoutManager = LinearLayoutManager(context)
            setController(controller)
        }
        viewModel.posts.observe(::getLifecycle) { posts -> controller.posts = posts }
    }

    private fun initSwipeRefresh() {
        viewModel.refreshing.observe(::getLifecycle) { refreshing ->
            if (!refreshing) tape_swipe_refresh.isRefreshing = false
        }
        tape_swipe_refresh.setOnRefreshListener { viewModel.mutate(Refresh) }
    }

    private fun initActions() {
        launch {
            viewModel.actions { action ->
                when (action) {
                    is ShowEnterSummonerScreen -> topNavController.navigate(R.id.enterSummonerScreen)
                    is ShowErrorSnack -> {
                        val message = if (action.error is NetworkError.CONNECTION)
                            R.string.tape_network_error
                        else
                            R.string.tape_error
                        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}