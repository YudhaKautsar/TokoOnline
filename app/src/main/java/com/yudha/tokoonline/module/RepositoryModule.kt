package com.yudha.tokoonline.module

import com.yudha.tokoonline.api.ApiService
import com.yudha.tokoonline.repository.UserRepository
import com.yudha.tokoonline.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }
}