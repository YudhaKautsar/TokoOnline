package com.yudha.tokoonline.base.adapter

import android.view.View
import androidx.annotation.IdRes

/**
 * Created by: Dimas Aprizawandi
 * Email: animatorist@gmail.com
 * @Date: 03/06/2022
 * Android Developer
 */
interface RecyclerViewItemClickListener<in T> {
    fun itemClick(position: Int, item: T?, @IdRes viewId: Int, view: View? = null, string: String? = null, isFile: Boolean = false)
}