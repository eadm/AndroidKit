package ru.nobird.android.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.nobird.android.ui.adapterdelegates.DelegateAdapter
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder
import ru.nobird.android.ui.adapters.diff.DiffCallbackFactory
import ru.nobird.android.ui.adapters.diff.IdentifiableDiffCallbackFactory

class DefaultDelegateAdapter<D>(
    private val diffCallbackFactory: DiffCallbackFactory<D> = IdentifiableDiffCallbackFactory()
) : DelegateAdapter<D, DelegateViewHolder<D>>() {
    var items: List<D> = emptyList()
        set(value) {
            DiffUtil
                .calculateDiff(diffCallbackFactory.createDiffUtil(field, value))
                .dispatchUpdatesTo(this)
            field = value
        }

    override fun getItemAtPosition(position: Int): D =
        items[position]

    override fun getItemCount(): Int =
        items.size
}