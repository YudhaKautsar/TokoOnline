package com.yudha.tokoonline.di

import com.yudha.tokoonline.module.AdapterModule
import com.yudha.tokoonline.module.AppModule
import com.yudha.tokoonline.module.NetworkModule
import com.yudha.tokoonline.module.RepositoryModule
import com.yudha.tokoonline.ui.login.LoginViewModel
import com.yudha.tokoonline.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, AppModule::class, AdapterModule::class])
interface AppComponent {
    fun inject(viewModel: LoginViewModel)
    fun inject(viewModel: MainViewModel)
}