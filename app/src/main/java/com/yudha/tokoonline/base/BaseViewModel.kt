package com.yudha.tokoonline.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

open class BaseViewModel : ViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    protected fun <T> safeApiCall(apiCall: suspend () -> Response<T>) {
        viewModelScope.launch {
            _loading.value = true // Set loading state to true
            try {
                val response = apiCall() // Call the API
                if (response.isSuccessful) {
                    handleSuccess(response.body()) // Handle success
                } else {
                    handleError(response.code(), response.message()) // Handle error
                }
            } catch (e: Exception) {
                handleError(-1, e.message ?: "An unknown error occurred") // Handle exception
            } finally {
                _loading.value = false // Set loading state to false
            }
        }
    }

    protected open fun <T> handleSuccess(data: T?) {
    }

    protected open fun handleError(code: Int, message: String) {
        val errorMessage = when (code) {
            400 -> "Bad Request: Please check your input."
            401 -> "Unauthorized: Invalid credentials."
            403 -> "Forbidden: You do not have access to this resource."
            404 -> "Not Found: The requested resource was not found."
            408 -> "Request Timeout: The server timed out waiting for the request."
            500 -> "Internal Server Error: Something went wrong on the server."
            502 -> "Bad Gateway: The server received an invalid response from the upstream server."
            503 -> "Service Unavailable: The server is currently unable to handle the request."
            504 -> "Gateway Timeout: The server did not receive a timely response from the upstream server."
            else -> "Error $code: $message" // Fallback for unknown errors
        }
        _error.value = errorMessage // Set error message to LiveData
    }
}