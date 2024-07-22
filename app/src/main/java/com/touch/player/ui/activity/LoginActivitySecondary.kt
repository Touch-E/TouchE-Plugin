package com.touch.player.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.touch.player.databinding.ActivityLoginSecondaryBinding
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

        binding.btnLogin.setOnClickListener {
            val loginRequest = LoginRequest(
                password = binding.edtPassword.text.toString().trim(),
                email = binding.edtEmail.text.toString().trim()
            )

            login.userAuthentication(loginRequest) { response ->
                if (response?.isSuccessful == true) {
                    processLogin(response.body())
                } else {
                    Toast.makeText(this, "Error logging you in", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun processLogin(data: LoginResponse?) {
        if (data != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Error logging you in", Toast.LENGTH_SHORT).show()
        }
    }
}