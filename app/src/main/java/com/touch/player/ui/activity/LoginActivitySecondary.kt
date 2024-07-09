package com.touch.player.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.touch.player.databinding.ActivityLoginSecondaryBinding
import com.touch.player.showToast
import com.touche.player.data.api.request.LoginRequest
import com.touche.player.data.api.response.LoginResponse
import com.touche.player.utils.OpenClass

class LoginActivitySecondary : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSecondaryBinding
    private lateinit var login: OpenClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSecondaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClick()
    }

    private fun setClick() {
        login = OpenClass(this)
//        if (SessionManager.getToken(this) != null) {
//            userToken = SessionManager.getToken(this).toString()
//        }
//
//        login.validateURLAndToken(urlString, userToken) { isURLValid, isTokenValid ->
//            if (isURLValid && isTokenValid) {
//                startActivity(Intent(this, MainActivity::class.java))
//                finish()
//            }
//        }

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
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            showToast("Error logging you in")
        }
    }
}