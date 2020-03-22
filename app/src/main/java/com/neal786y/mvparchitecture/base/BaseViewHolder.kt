package com.dev.neal786y.mvpsample.base

import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    abstract fun loadData(data: T?)
}