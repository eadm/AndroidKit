package ru.nobird.android.ui.adapterssupport.diff

import android.support.v7.util.DiffUtil

class IdentifiableDiffCallbackFactory<T> :
    DiffCallbackFactory<T> {
    override fun createDiffUtil(oldList: List<T>, newList: List<T>): DiffUtil.Callback =
        IdentifiableDiffCallback(oldList, newList)
}