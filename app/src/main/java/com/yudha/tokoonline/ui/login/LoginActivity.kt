package com.yudha.tokoonline.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yudha.tokoonline.R
import com.yudha.tokoonline.databinding.ActivityLoginBinding
import com.yudha.tokoonline.result.LoginResult
import com.yudha.tokoonline.result.token.TokenManager
import com.yudha.tokoonline.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (tokenManager.getToken() != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        binding.buttonLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            binding.buttonLogin.isLoading = true

            loginViewModel.login(username, password)
        }

        observeLoginResult()
    }

    private fun observeLoginResult() {
        loginViewModel.loginResult.observe(this) { result ->
            when (result) {
                is LoginResult.Success -> {
                    binding.buttonLogin.isLoading = false
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is LoginResult.Failure -> {
                    binding.buttonLogin.isLoading = false
                    Toast.makeText(this, getString(R.string.lbl_username_password_salah), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}