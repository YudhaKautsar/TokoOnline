package com.yudha.tokoonline.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudha.tokoonline.api.model.request.LoginRequest
import com.yudha.tokoonline.api.model.response.LoginResponse
import com.yudha.tokoonline.base.BaseViewModel
import com.yudha.tokoonline.repository.UserRepository
import com.yudha.tokoonline.result.LoginResult
import com.yudha.tokoonline.result.token.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : BaseViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> get() = _loginResult

    fun login(username: String, password: String) {
        val request = LoginRequest(username, password)
        safeApiCall {
            userRepository.loginUser(request)
        }
    }

    override fun <T> handleSuccess(data: T?) {
        if (data is LoginResponse) {
            data.token?.let { token ->
                tokenManager.saveToken(token)
                _loginResult.value = LoginResult.Success(token)
            } ?: run {
                _loginResult.value = LoginResult.Failure
            }
        }
    }

    override fun handleError(code: Int, message: String) {
        super.handleError(code, message)
        _loginResult.value = LoginResult.Failure
    }
}