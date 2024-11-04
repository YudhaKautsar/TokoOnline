package com.yudha.tokoonline.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudha.tokoonline.api.ApiService
import com.yudha.tokoonline.api.model.response.ProductResponse
import com.yudha.tokoonline.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : BaseViewModel() {

    private val _products = MutableLiveData<List<ProductResponse>>()
    private val _errorMessage = MutableLiveData<String>()

    fun fetchProducts(limit: Int = 10) {
        safeApiCall { apiService.getProducts(limit) }
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