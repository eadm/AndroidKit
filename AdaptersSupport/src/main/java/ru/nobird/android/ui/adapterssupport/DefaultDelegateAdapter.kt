package ru.nobird.android.ui.adapterssupport

import android.support.v7.util.DiffUtil
import ru.nobird.android.ui.adapterdelegatessupport.DelegateAdapter
import ru.nobird.android.ui.adapterdelegatessupport.DelegateViewHolder
import ru.nobird.android.ui.adapterssupport.diff.DiffCallbackFactory
import ru.nobird.android.ui.adapterssupport.diff.IdentifiableDiffCallbackFactory

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