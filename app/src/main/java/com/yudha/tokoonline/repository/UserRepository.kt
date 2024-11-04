package com.yudha.tokoonline.repository

import com.yudha.tokoonline.api.model.request.LoginRequest
import com.yudha.tokoonline.api.model.response.LoginResponse
import com.yudha.tokoonline.api.model.response.ProductResponse
import retrofit2.Response

interface UserRepository {
    suspend fun loginUser (loginRequest: LoginRequest): Response<LoginResponse>

    suspend fun fetchProducts(limit: Int): Response<List<ProductResponse>>
}