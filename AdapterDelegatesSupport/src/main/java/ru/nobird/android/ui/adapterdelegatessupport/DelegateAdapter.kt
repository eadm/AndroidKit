package ru.nobird.android.ui.adapterdelegatessupport

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class DelegateAdapter<D, VH : DelegateViewHolder<D>> : RecyclerView.Adapter<VH>() {
    private val _delegates = mutableListOf<AdapterDelegate<D, VH>>()

    val delegates: List<AdapterDelegate<D, VH>>
        get() = _delegates

    fun addDelegate(delegate: AdapterDelegate<D, VH>) {
        if (delegate !in _delegates) {
            _delegates.add(delegate)
        }
    }

    fun removeDelegate(delegate: AdapterDelegate<D, VH>) {
        _delegates.remove(delegate)
    }

    operator fun plusAssign(delegate: AdapterDelegate<D, VH>) {
        addDelegate(delegate)
    }

    operator fun minusAssign(delegate: AdapterDelegate<D, VH>) {
        removeDelegate(delegate)
    }

    override fun getItemViewType(position: Int): Int =
        delegates.indexOfFirst { it.isForViewType(position, getItemAtPosition(position)) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItemAtPosition(position))
    }

    override fun onViewRecycled(holder: VH) {
        holder.unbind()
    }

    abstract fun getItemAtPosition(position: Int): D
}