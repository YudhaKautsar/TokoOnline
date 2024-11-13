package com.yudha.tokoonline.api.model.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yudha.tokoonline.api.model.localDatabase.dao.ProductDao
import com.yudha.tokoonline.api.model.localDatabase.dao.UserDao
import com.yudha.tokoonline.api.model.localDatabase.table.ProductEntity
import com.yudha.tokoonline.api.model.localDatabase.table.User

@Database(entities = [User::class, ProductEntity::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}