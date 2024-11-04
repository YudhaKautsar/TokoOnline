package com.yudha.tokoonline.result

// LoginResult.kt
sealed class LoginResult {
    data class Success(val token: String) : LoginResult()
    object Failure : LoginResult()
}