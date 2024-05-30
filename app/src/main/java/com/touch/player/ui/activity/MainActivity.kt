package com.touch.player.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.touch.player.databinding.ActivityMainBinding
import com.touch.player.setPreviewBothSide
import com.touche.player.R
import com.touche.player.data.api.response.AllContentsResponse
import com.touche.player.ui.activity.AccountDetailsActivity
import com.touche.player.ui.activity.CartActivity
import com.touche.player.ui.activity.MovieDetailActivity
import com.touche.player.ui.adapter.ViewPagerAdapter
import com.touche.player.utils.OpenClass
import com.touche.player.utils.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var homeScreen: OpenClass

    private var data: List<AllContentsResponse>? = null

    val urlString = "https://api-cluster.system.touchetv.com"
    var userToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showLoading()
        homeScreen = OpenClass(this)

        userToken = SessionManager.getToken(this).orEmpty()

        homeScreen.validateURLAndToken(urlString, userToken) { isURLValid, isTokenValid ->
            if (isURLValid && isTokenValid) {
                fetchMovieDetails()
                fetchCartDataCount()
            } else {
                navigateToLogin()
            }
        }

        binding.ivUser.setOnClickListener {
            startActivity(Intent(this, AccountDetailsActivity::class.java))
        }

        binding.ivCart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun fetchMovieDetails() {
        lifecycleScope.launch {
            homeScreen.getMovieDetail {
                processData(it)
            }
        }
    }

    private fun fetchCartDataCount() {
        lifecycleScope.launch {
            homeScreen.getCartDataCount { cartData ->
                cartData?.let {
                    binding.tvCartItemsNumber.visibility =
                        if (it.isNotEmpty()) View.VISIBLE else View.GONE
                    binding.tvCartItemsNumber.text = " ${it.size} "
                }
                stopLoading()
            }
        }
    }

    private fun processData(responses: List<AllContentsResponse>?) {
        if (!responses.isNullOrEmpty()) {
            data = responses
            init(responses)

            val userImageUrl = SessionManager.getUserImageUrl(this)
            if (userImageUrl != null) {
                if (userImageUrl.isNotEmpty()) {
                    Glide.with(this)
                        .load(userImageUrl)
                        .placeholder(R.drawable.user_bg_icon)
                        .into(binding.ivUser)
                }
            }
        }
        stopLoading()
    }

    private fun init(data: List<AllContentsResponse>) {
        val adapter = ViewPagerAdapter(data) { movieID ->
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("movieID", movieID)
            startActivity(intent)
        }

        binding.viewPager2.apply {
            this.adapter = adapter
            setPreviewBothSide(R.dimen._40sdp, R.dimen._50sdp)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 10
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        binding.dotsIndicator.apply {
            attachTo(binding.viewPager2)
            visibility = View.VISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        fetchCartDataCount()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivitySecondary::class.java))
        finish()
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }
}