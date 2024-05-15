package com.touch.player.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.touch.player.R
import com.touch.player.databinding.ActivityLoginSecondaryBinding
import com.touch.player.databinding.ActivityMainBinding
import com.touche.player.utils.OpenClass
import com.touche.player.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeScreen: OpenClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeScreen = OpenClass()

        homeScreen.getMovieDetail {
            Log.d("OKL", "getMovieDetail: $it")
        }
        homeScreen.getCartDataCount(SessionManager.getUserId(this)) {
            Log.d("OKL", "getCartDataCount: $it")
        }
    }
}