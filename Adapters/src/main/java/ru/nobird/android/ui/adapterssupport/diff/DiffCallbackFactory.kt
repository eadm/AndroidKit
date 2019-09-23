package ru.nobird.android.ui.adapterssupport.diff

import androidx.recyclerview.widget.DiffUtil

interface DiffCallbackFactory<T> {
    fun createDiffUtil(oldList: List<T>, newList: List<T>): DiffUtil.Callback
}