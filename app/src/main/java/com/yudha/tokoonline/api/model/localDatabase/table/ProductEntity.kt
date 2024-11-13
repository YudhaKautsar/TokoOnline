package com.yudha.tokoonline.api.model.localDatabase.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: String,
    val image: String,
    val quantity: Int
)