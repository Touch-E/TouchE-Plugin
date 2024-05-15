package com.touch.player.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.touch.player.databinding.ActivityMainBinding
import com.touch.player.setPreviewBothSide
import com.touche.player.R
import com.touche.player.data.api.response.AllContentsResponse
import com.touche.player.data.api.response.LoginResponse
import com.touche.player.ui.activity.AccountDetailsActivity
import com.touche.player.ui.activity.CartActivity
import com.touche.player.ui.activity.MovieDetailActivity
import com.touche.player.ui.adapter.ViewPagerAdapter
import com.touche.player.utils.OpenClass
import com.touche.player.utils.SessionManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeScreen: OpenClass
    private var loginResponse: LoginResponse? = null
    private var data: List<AllContentsResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()
        homeScreen = OpenClass(this)
        homeScreen.getMovieDetail {
            processData(it)
        }

        homeScreen.getCartDataCount {
            if (it != null) {
                if (listOf(it).isNotEmpty()) binding.tvCartItemsNumber.visibility = View.VISIBLE

                if (it.size.toString().length == 1 || it.size.toString().length == 2) {
                    binding.tvCartItemsNumber.text = " ${it.size} "
                } else {
                    binding.tvCartItemsNumber.text = it.size.toString()
                }
            }
        }

        binding.ivUser.setOnClickListener {
            startActivity(Intent(this, AccountDetailsActivity::class.java))
        }

        binding.ivCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    fun processData(responses: List<AllContentsResponse>?) {
        if (!responses.isNullOrEmpty()) {
            data = responses

            stopLoading()
            init(responses)

            if (SessionManager.getUserImageUrl(this) != "") {
                Glide.with(this).load(SessionManager.getUserImageUrl(this))
                    .placeholder(R.drawable.user_bg_icon).into(binding.ivUser)
            }
        } else {
            stopLoading()
        }
    }

    private fun init(data: List<AllContentsResponse>) {
        val adapter = ViewPagerAdapter(data) { movieID ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("movieID", movieID)
            startActivity(intent)
        }

        binding.viewPager2.adapter = adapter

        binding.viewPager2.setPreviewBothSide(R.dimen._40sdp, R.dimen._50sdp)

        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 10
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        binding.dotsIndicator.attachTo(binding.viewPager2)
        binding.dotsIndicator.visibility = View.VISIBLE

        stopLoading()
    }

    override fun onResume() {
        super.onResume()
        loginResponse?.let {
            homeScreen.getCartDataCount {
                if (it != null) {
                    if (listOf(it).isNotEmpty()) binding.tvCartItemsNumber.visibility = View.VISIBLE

                    if (it.size.toString().length == 1 || it.size.toString().length == 2) {
                        binding.tvCartItemsNumber.text = " ${it.size} "
                    } else {
                        binding.tvCartItemsNumber.text = it.size.toString()
                    }
                }

                stopLoading()
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }
}