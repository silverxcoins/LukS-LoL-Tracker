package ru.mobile.lukslol.view.tape

import androidx.databinding.ObservableField
import kotlinx.coroutines.launch
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.ServiceType.*
import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.domain.dto.Summoner
import ru.mobile.lukslol.domain.repository.FeedRepository
import ru.mobile.lukslol.domain.repository.SummonerRepository
import ru.mobile.lukslol.util.type.NonNullLiveData
import ru.mobile.lukslol.view.BaseViewModel
import ru.mobile.lukslol.view.screenresult.ScreenResultProvider
import ru.mobile.lukslol.view.start.EnterSummonerScreenResult
import ru.mobile.lukslol.view.tape.TapeAction.*
import ru.mobile.lukslol.view.tape.TapeMutation.*
import javax.inject.Inject

class TapeViewModel : BaseViewModel<TapeMutation, TapeAction>() {

    companion object {
        private const val POSTS_PAGE_SIZE = 10
    }

    @Inject
    lateinit var screenResultProvider: ScreenResultProvider
    @Inject
    lateinit var summonerRepository: SummonerRepository
    @Inject
    lateinit var feedRepository: FeedRepository

    init {
        Components.tapeScreenComponent.create(this)
        Components.appComponent.get().inject(this)

        loadSummoner()
        collectSummonerFromEnterScreen()
    }

    val summoner = ObservableField<Summoner>()
    val posts = NonNullLiveData(emptyList<Post>())
    val refreshing = NonNullLiveData(true)

    private var postServiceType: ServiceType = DB

    override fun update(mutation: TapeMutation) {
        when (mutation) {
            is SummonerReceived -> {
                summoner.set(mutation.summoner)
                refreshing.value = false
                loadPosts(DB, fromStart = true)
                loadPosts(NETWORK, fromStart = true)
            }
            is NoSummonerInDb, SummonerIconClick -> action(ShowEnterSummonerScreen)
            is Refresh -> {
                if (!refreshing.value) {
                    refreshing.value = true
                    loadPosts(NETWORK, fromStart = true)
                }
            }
            is PostsReceived -> {
                posts.value = when (postServiceType) {
                    DB -> {
                        if (mutation.serviceType == DB) {
                            posts.value + mutation.posts
                        } else {
                            refreshing.value = false
                            postServiceType = NETWORK
                            mutation.posts
                        }
                    }
                    NETWORK -> {
                        if (mutation.serviceType == NETWORK) {
                            val refreshing = refreshing.value
                            this.refreshing.value = false
                            if (refreshing) mutation.posts else posts.value + mutation.posts
                        } else {
                            posts.value
                        }
                    }
                    else -> throw NotImplementedError()
                }
            }
            is PostsFailed -> {
                if (mutation.serviceType == NETWORK) {
                    refreshing.value = false
                    action(ShowErrorSnack(mutation.error))
                }
            }
        }
    }

    override fun onCleared() {
        Components.tapeScreenComponent.clear()
        super.onCleared()
    }

    private fun loadSummoner() {
        launch {
            try {
                summonerRepository.getCurrentSummoner(DB)
                    ?.also { summoner -> mutate(SummonerReceived(summoner)) }
                    ?: mutate(NoSummonerInDb)
            } catch (e: Exception) {
                mutate(NoSummonerInDb)
            }
        }
    }

    private fun collectSummonerFromEnterScreen() {
        launch {
            screenResultProvider.collectResults<EnterSummonerScreenResult> { result ->
                mutate(SummonerReceived(result.summoner))
            }
        }
    }

    private fun loadPosts(serviceType: ServiceType, fromStart: Boolean) {
        launch {
            try {
                val posts = feedRepository.getPosts(
                    service = serviceType,
                    limit = POSTS_PAGE_SIZE,
                    offset = if (fromStart) 0 else this@TapeViewModel.posts.value.size,
                    resetInDb = fromStart
                )
                mutate(PostsReceived(serviceType, posts))
            } catch (e: Exception) {
                mutate(PostsFailed(serviceType, e))
            }
        }
    }
}