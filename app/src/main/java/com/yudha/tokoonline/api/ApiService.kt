package com.yudha.tokoonline.api

import com.yudha.tokoonline.api.model.request.LoginRequest
import com.yudha.tokoonline.api.model.response.LoginResponse
import com.yudha.tokoonline.api.model.response.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun loginUser (@Body request: LoginRequest): Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int): Response<List<ProductResponse>>
}