package com.yudha.tokoonline.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yudha.tokoonline.api.model.response.ProductResponse
import com.yudha.tokoonline.base.BaseViewModel
import com.yudha.tokoonline.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
): BaseViewModel() {

    private val _productDetail = MutableLiveData<ProductResponse>()
    val productDetail: LiveData<ProductResponse> get() = _productDetail
    private val _errorMessage = MutableLiveData<String>()

    fun getProductDetail(id: String) {
        safeApiCall { userRepository.getProductDetail(id) }
    }

    override fun <T> handleSuccess(data: T?) {
        if (data is ProductResponse) {
            _productDetail.value = data
        }
    }

    override fun handleError(code: Int, message: String) {
        super.handleError(code, message)
        _errorMessage.value = message
    }
}