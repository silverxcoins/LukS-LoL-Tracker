package ru.mobile.lukslol.view.tape

import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.domain.dto.Summoner

sealed class TapeMutation {
    data class SummonerReceived(val summoner: Summoner) : TapeMutation()
    object NoSummonerInDb : TapeMutation()
    object SummonerIconClick : TapeMutation()
    data class PostsReceived(val posts: List<Post>) : TapeMutation()
    data class PostsFailed(val error: Exception) : TapeMutation()
}

sealed class TapeAction {
    object ShowEnterSummonerScreen : TapeAction()
}