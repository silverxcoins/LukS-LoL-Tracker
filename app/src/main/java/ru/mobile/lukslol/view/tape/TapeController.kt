package ru.mobile.lukslol.view.tape

import com.airbnb.epoxy.EpoxyController
import ru.mobile.lukslol.domain.dto.Post

class TapeController : EpoxyController() {

    var posts = emptyList<Post>()
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
                is Post.Greeting -> {
                    tapeGreetingView {
                        id(post.id)
                        post(post)
                    }
                }
            }
        }
    }
}