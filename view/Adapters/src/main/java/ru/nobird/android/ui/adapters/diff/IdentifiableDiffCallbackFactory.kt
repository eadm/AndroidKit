package ru.nobird.android.ui.adapters.diff

import androidx.recyclerview.widget.DiffUtil

class IdentifiableDiffCallbackFactory<T> :
    DiffCallbackFactory<T> {
    override fun createDiffUtil(oldList: List<T>, newList: List<T>): DiffUtil.Callback =
        IdentifiableDiffCallback(oldList, newList)
}