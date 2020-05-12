package ru.mobile.lukslol.view.tape

import ru.mobile.lukslol.domain.ServiceType
import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.domain.dto.Summoner

sealed class TapeMutation {
    data class SummonerReceived(val summoner: Summoner) : TapeMutation()
    object NoSummonerInDb : TapeMutation()
    object SummonerIconClick : TapeMutation()
    object Refresh : TapeMutation()
    data class PostsReceived(val serviceType: ServiceType, val posts: List<Post>) : TapeMutation()
    data class PostsFailed(val serviceType: ServiceType, val error: Exception) : TapeMutation()
    object NeedMorePosts : TapeMutation()
}

sealed class TapeAction {
    object ShowEnterSummonerScreen : TapeAction()
    data class ShowErrorSnack(val error: Exception) : TapeAction()
}