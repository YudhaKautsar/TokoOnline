package com.yudha.tokoonline.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by: Dimas Aprizawandi
 * Email: animatorist@gmail.com
 * @Date: 03/06/2022
 * Android Developer
 */
abstract class BaseHolder<T> constructor(val listener: RecyclerViewItemClickListener<T>, itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private var itemPosition: Int = 0
    private var itemData: T? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        listener.itemClick(itemPosition, itemData, view.id)
    }

    fun bindData(position: Int, data: T?) {
        itemPosition = position
        itemData = data
    }

    fun getData(): T? = itemData

    fun getDataPosition(): Int = itemPosition
}