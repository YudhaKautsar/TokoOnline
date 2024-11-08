package com.yudha.tokoonline.repository


import com.yudha.tokoonline.api.ApiService
import com.yudha.tokoonline.api.model.request.LoginRequest
import com.yudha.tokoonline.api.model.response.LoginResponse
import com.yudha.tokoonline.api.model.response.ProductResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.loginUser(loginRequest)
    }

    override suspend fun fetchProducts(): Response<List<ProductResponse>> {
        return apiService.getProducts()
    }

    override suspend fun getProductDetail(id: String?): Response<ProductResponse> {
        return apiService.getProductDetail(id)
    }

}