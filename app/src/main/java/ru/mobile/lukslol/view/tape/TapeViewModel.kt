package ru.mobile.lukslol.view.tape

import androidx.databinding.ObservableField
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
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
import ru.mobile.lukslol.view.pagination.PaginationType.*
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
    val pagination = NonNullLiveData(NONE)
    val refreshing = NonNullLiveData(false)

    private var postServiceType: ServiceType = DB

    private var postsJob: Job? = null

    private var postsEnded = false


    override fun update(mutation: TapeMutation) {
        when (mutation) {
            is SummonerReceived -> {
                summoner.set(mutation.summoner)
                loadPosts(DB, fromStart = true)
                loadPosts(NETWORK, fromStart = true)
            }
            is NoSummonerInDb, SummonerIconClick -> action(ShowEnterSummonerScreen)
            is Refresh -> {
                if (!refreshing.value) {
                    postsJob?.cancel()
                    postsEnded = false
                    refreshing.value = true
                    loadPosts(NETWORK, fromStart = true)
                }
            }
            is PostsReceived -> {
                pagination.value = NONE
                if (mutation.posts.size < POSTS_PAGE_SIZE) postsEnded = true
                posts.value = when (postServiceType) {
                    DB -> {
                        if (mutation.serviceType == DB) {
                            posts.value + mutation.posts
                        } else {
                            refreshing.value = false
                            postServiceType = NETWORK
                            postsEnded = false
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
                    if (refreshing.value || postServiceType == DB) {
                        refreshing.value = false
                        pagination.value = NONE
                        action(ShowErrorSnack(mutation.error))
                    } else {
                        pagination.value = ERROR
                    }
                }
            }
            is NeedMorePosts -> {
                if (!postsEnded && postsJob?.isActive != true) {
                    pagination.value = LOADING
                    loadPosts(postServiceType, fromStart = false)
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
        postsJob = launch {
            try {
                val posts = feedRepository.getPosts(
                    service = serviceType,
                    limit = POSTS_PAGE_SIZE,
                    offset = if (fromStart) 0 else this@TapeViewModel.posts.value.size,
                    resetInDb = fromStart
                )
                if (isActive) {
                    mutate(PostsReceived(serviceType, posts))
                }
            } catch (e: Exception) {
                if (isActive) {
                    mutate(PostsFailed(serviceType, e))
                }
            }
        }
    }
}