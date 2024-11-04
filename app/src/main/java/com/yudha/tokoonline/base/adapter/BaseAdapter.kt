package com.yudha.tokoonline.base.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by: Dimas Aprizawandi
 * Email: animatorist@gmail.com
 * @Date: 03/06/2022
 * Android Developer
 */
abstract class BaseAdapter<S, T : BaseHolder<S>>: RecyclerView.Adapter<T>() {
    lateinit var listener: RecyclerViewItemClickListener<S>
    var list: ArrayList<S> = ArrayList()

    fun setItemClickListener(listener: RecyclerViewItemClickListener<S>) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        val data = getItem(position)
        holder.bindData(position, data)
        bindViewHolder(holder, data)
    }

    fun add(data: S) {
        list.add(data)
        notifyItemInserted(list.size)
    }

    fun getDataList() : ArrayList<S>{
        return list
    }

    open fun addAll(dataList: ArrayList<S>) {
        for (item: S in dataList) {
            add(item)
        }
    }

    open fun addAll(dataList: List<S>) {
        for (item: S in dataList) {
            add(item)
        }
    }

    open fun removeItem(pos: Int) {
        list.removeAt(pos)
    }

    open fun remove(pos: Int) {
        list.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) =
        if (position < list.size) {
            list[position]
        } else {
            null
        }

    override fun getItemCount() = list.size

    protected abstract fun bindViewHolder(holder: T, data: S?)
}