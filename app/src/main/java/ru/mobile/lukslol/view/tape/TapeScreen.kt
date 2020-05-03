package ru.mobile.lukslol.view.tape

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
    lateinit var screenResultProvider: ScreenResultProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewModelProviders.of(this).get(TapeViewModel::class.java)
        Components.tapeScreenComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_tape, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        screenResultProvider.results<EnterSummonerScreenResult>().subscribe { result ->
            name.text = result.summoner.name
            Glide.with(this)
                .load(result.summoner.icon)
                .onFinish {  }
                .into(icon)
        }.addTo(disposable)
    }


}

fun <T> RequestBuilder<T>.onFinish(onSuccess: (() -> Unit)? = null, onError: (() -> Unit)? = null): RequestBuilder<T> {
    return listener(object : RequestListener<T> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<T>?, isFirstResource: Boolean): Boolean {
            onError?.invoke()
            return false
        }

        override fun onResourceReady(resource: T, model: Any?, target: Target<T>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            onSuccess?.invoke()
            return false
        }
    })
}