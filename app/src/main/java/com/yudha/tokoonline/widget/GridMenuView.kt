package com.yudha.tokoonline.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView
import androidx.recyclerview.widget.RecyclerView
import com.yudha.tokoonline.MyApplication
import com.yudha.tokoonline.ui.main.adapter.ProductAdapter
import javax.inject.Inject


class GridMenuView<T : RecyclerView.Adapter<*>> : GridView {
    private var _adapter: T? = null

    val adapter: T?
        get() = _adapter

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )


    fun setAdapter(adapter: T) {
        this._adapter = adapter
        // Optionally set the adapter to GridView if needed
        adapter.notifyDataSetChanged()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightSpec: Int = if (layoutParams.height == LayoutParams.WRAP_CONTENT) {
            MeasureSpec.makeMeasureSpec(
                Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST
            )
        } else {
            heightMeasureSpec
        }
        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}
