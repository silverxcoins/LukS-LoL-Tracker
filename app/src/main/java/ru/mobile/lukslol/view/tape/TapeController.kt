package ru.mobile.lukslol.view.tape

import com.airbnb.epoxy.EpoxyController
import ru.mobile.lukslol.domain.dto.Post
import ru.mobile.lukslol.view.pagination.PaginationType.*
import ru.mobile.lukslol.view.pagination.paginationErrorView
import ru.mobile.lukslol.view.pagination.paginationLoadingView

class TapeController(private val onRetryClick: () -> Unit) : EpoxyController() {

    var posts = emptyList<Post>()
        set(value) {
            field = value
            requestModelBuild()
        }
    var pagination = NONE
        set(value) {
            field = value
            requestModelBuild()
        }

    init {
        requestModelBuild()
    }

    override fun buildModels() {
        posts.forEach { post ->
            when (post) {
                is Post.Greeting ->
                    tapeGreetingView {
                        id(post.id)
                        post(post)
                    }
            }
        }
        when (pagination) {
            LOADING ->
                paginationLoadingView {
                    id("pagination_loading")
                }
            ERROR ->
                paginationErrorView {
                    id("pagination_error")
                    onClick(onRetryClick)
                }
            else -> {}
        }
    }
}