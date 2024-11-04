package com.yudha.tokoonline.repository


import com.yudha.tokoonline.api.ApiService
import com.yudha.tokoonline.api.model.request.LoginRequest
import com.yudha.tokoonline.api.model.response.LoginResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val apiService: ApiService) : UserRepository {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.loginUser(loginRequest)
    }

    /*override suspend fun getUser Data(userId: Int): Response<User> {
        return apiService.getUser Data(userId)
    }*/
}