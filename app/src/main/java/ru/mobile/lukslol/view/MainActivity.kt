package ru.mobile.lukslol.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_main.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.view.start.EnterSummonerScreen

class MainActivity : AppCompatActivity() {

    private val backPressedListeners = mutableListOf<BackPressedListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigation.findNavController(this, R.id.top_navigation_fragment).navigate(R.id.enterSummonerScreen)
    }

    fun addBackPressedListener(listener: BackPressedListener) {
        if (backPressedListeners.contains(listener)) {
            backPressedListeners.remove(listener)
        }
        backPressedListeners.add(listener)
    }

    fun removeBackPressedListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun onBackPressed() {
        val backProcessed = backPressedListeners.lastOrNull()?.onBackPressed() == true
        if (!backProcessed) {
            super.onBackPressed()
        }
    }
}
