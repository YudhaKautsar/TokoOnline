package com.yudha.tokoonline.module

import com.yudha.tokoonline.ui.main.bottomsheet.SinglePickerRecyclerKategoriBottomSheet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class) // or another suitable component
object BottomSheetModule {

    @Provides
    fun provideSinglePickerRecyclerKategoriBottomSheet(): SinglePickerRecyclerKategoriBottomSheet {
        return SinglePickerRecyclerKategoriBottomSheet()
    }
}