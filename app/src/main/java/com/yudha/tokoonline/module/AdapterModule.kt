package com.yudha.tokoonline.module

import com.yudha.tokoonline.ui.main.adapter.ProductAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {

    @Provides
    @ActivityScoped
    fun provideProductAdapter(): ProductAdapter {
        return ProductAdapter()
    }
}