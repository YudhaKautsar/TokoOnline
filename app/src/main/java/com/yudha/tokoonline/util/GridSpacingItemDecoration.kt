package com.yudha.tokoonline.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view) // Get item position
        val column = position % spanCount // Get column index (0 to spanCount - 1)

        // Set left and right margins
        outRect.left = if (column > 0) spacing / 2 else 0 // No left margin for the first column
        outRect.right = if (column < spanCount - 1) spacing / 2 else 0 // No right margin for the last column

        // Set top margin
        if (position < spanCount) { // Only apply top margin for the first row
            outRect.top = spacing
        }
        outRect.bottom = spacing // Apply bottom margin for all items
    }
}