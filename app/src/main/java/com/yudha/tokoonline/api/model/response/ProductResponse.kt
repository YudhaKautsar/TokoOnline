package com.yudha.tokoonline.api.model.response

data class ProductResponse(
    val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String
)