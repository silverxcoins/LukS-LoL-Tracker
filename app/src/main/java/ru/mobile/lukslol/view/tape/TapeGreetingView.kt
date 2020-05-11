package ru.mobile.lukslol.view.tape

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ru.mobile.lukslol.R
import ru.mobile.lukslol.databinding.ViewTapePostGreetingBinding
import ru.mobile.lukslol.domain.dto.Post

@ModelView(defaultLayout = R.layout.view_tape_post_greeting)
class TapeGreetingView : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    lateinit var binding: ViewTapePostGreetingBinding

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewTapePostGreetingBinding.bind(this)
    }

    @ModelProp
    fun post(post: Post.Greeting) {
        binding.post = post
    }
}