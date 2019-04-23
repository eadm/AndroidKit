package ru.nobird.android.ui.adapterdelegatessupport

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

abstract class DelegateViewHolder<D>(root: View) : RecyclerView.ViewHolder(root) {
    protected var itemData: D? = null
//        private set // should be private but can't be enabled due to https://youtrack.jetbrains.com/issue/KT-22465

    protected val context: Context
        get() = itemView.context

    internal fun bind(data: D) {
        itemData = data
        onBind(data)
    }

    internal fun unbind() {
        itemData = null
        onUnbind()
    }

    protected open fun onBind(data: D) {}
    protected open fun onUnbind() {}
}