package com.yudha.tokoonline.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudha.tokoonline.api.ApiService
import com.yudha.tokoonline.api.model.response.ProductResponse
import com.yudha.tokoonline.base.BaseViewModel
import com.yudha.tokoonline.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _products = MutableLiveData<List<ProductResponse>>()
    val products: LiveData<List<ProductResponse>> get() = _products
    private val _errorMessage = MutableLiveData<String>()

    fun fetchProducts(limit: Int = 10) {
        safeApiCall { userRepository.fetchProducts(limit) }
    }

    override fun <T> handleSuccess(data: T?) {
        if (data is List<*>) {
            _products.value =
                data.filterIsInstance<ProductResponse>()
        }
    }

    override fun handleError(code: Int, message: String) {
        super.handleError(code, message)
        _errorMessage.value = message
    }
}