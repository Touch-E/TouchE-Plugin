package com.touch.player.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.touch.player.databinding.ActivityLoginSecondaryBinding
import com.touch.player.showToast
import com.touche.player.data.api.request.LoginRequest
import com.touche.player.data.api.response.LoginResponse
import com.touche.player.utils.OpenClass

class LoginActivitySecondary : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSecondaryBinding
    private lateinit var login: OpenClass

    val urlString = "https://api-cluster.system.touchetv.com"
    var userToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClick()
    }

    private fun setClick() {
        login = OpenClass(this)

        login.validateURLAndToken(urlString, userToken) { isURLValid, isTokenValid ->
            Log.d("TouchEPlugin Log", "urlString: $urlString, $userToken")
            Log.d("TouchEPlugin Log", "validateURLAndToken: $isURLValid, $isTokenValid")
        }

        binding.btnLogin.setOnClickListener {
            val loginRequest = LoginRequest(
                password = binding.edtPassword.text.toString().trim(),
                email = binding.edtEmail.text.toString().trim()
            )

            login.userAuthentication(loginRequest) { response ->
                if (response?.isSuccessful == true) {
                    processLogin(response.body())
                } else {
                    showToast("Error logging you in")
                }
            }
        }
    }

    private fun processLogin(data: LoginResponse?) {
        if (data != null) {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("login_response", data)
            }
            startActivity(intent)
            finish()
        } else {
            showToast("Error logging you in")
        }
    }
}