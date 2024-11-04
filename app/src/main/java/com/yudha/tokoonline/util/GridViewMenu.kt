package com.yudha.tokoonline.util

import android.content.Context
import android.util.AttributeSet
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yudha.tokoonline.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GridViewMenu @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {


    init {
        setupRecyclerView(context)
    }

    private fun setupRecyclerView(context: Context) {
        val spacing = context.resources.getDimensionPixelSize(R.dimen.space_x2)
        layoutManager = GridLayoutManager(context, 2) // Set grid layout with 2 columns
        addItemDecoration(GridSpacingItemDecoration(2, spacing)) // Add spacing decoration
    }
    fun setCustomAdapter(adapter: Adapter<*>) { // Renamed to avoid conflict
        super.setAdapter(adapter) // Set the adapter to the RecyclerView
    }

}