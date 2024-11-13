package com.yudha.tokoonline.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yudha.tokoonline.api.model.localDatabase.AppDatabase
import com.yudha.tokoonline.api.model.localDatabase.ProductDbQualifier
import com.yudha.tokoonline.api.model.localDatabase.dao.ProductDao
import com.yudha.tokoonline.api.model.localDatabase.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }
}