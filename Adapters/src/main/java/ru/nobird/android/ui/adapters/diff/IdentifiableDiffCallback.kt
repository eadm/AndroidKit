package ru.nobird.android.ui.adapters.diff

import androidx.recyclerview.widget.DiffUtil
import ru.nobird.android.core.model.Identifiable

class IdentifiableDiffCallback<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int =
        oldList.size

    override fun getNewListSize(): Int =
        newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        if (oldItem == newItem) return true

        if (oldItem is Identifiable<*> &&
            newItem is Identifiable<*> &&
            oldItem.javaClass == newItem.javaClass && // cause different classes can have the same ids
            oldItem.id == newItem.id) {
            return true
        }

        return false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}