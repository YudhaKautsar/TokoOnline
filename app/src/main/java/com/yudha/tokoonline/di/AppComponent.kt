package com.yudha.tokoonline.di

import com.yudha.tokoonline.network.NetworkModule
import com.yudha.tokoonline.repository.RepositoryModule
import com.yudha.tokoonline.ui.login.LoginViewModel
import com.yudha.tokoonline.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: MainViewModel)
}