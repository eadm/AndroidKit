package ru.nobird.android.ui.adapterssupport.diff

import android.support.v7.util.DiffUtil

interface DiffCallbackFactory<T> {
    fun createDiffUtil(oldList: List<T>, newList: List<T>): DiffUtil.Callback
}