package ru.nobird.android.ui.adapterdelegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

abstract class AdapterDelegate<D, VH : DelegateViewHolder<D>> {
    protected fun createView(parent: ViewGroup, @LayoutRes layoutId: Int): View =
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

    abstract fun onCreateViewHolder(parent: ViewGroup): VH
    abstract fun isForViewType(position: Int, data: D): Boolean
}