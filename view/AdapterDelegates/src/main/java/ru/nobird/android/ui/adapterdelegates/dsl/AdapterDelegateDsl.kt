package ru.nobird.android.ui.adapterdelegates.dsl

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import ru.nobird.android.ui.adapterdelegates.AdapterDelegate
import ru.nobird.android.ui.adapterdelegates.DelegateViewHolder

inline fun <D, reified DT : D> adapterDelegate(
    @LayoutRes layoutResId: Int,
    crossinline isForViewType: (position: Int, data: D) -> Boolean = { _, data: D -> data is DT },
    crossinline block: DslDelegateViewHolder<D, DT>.() -> Unit = {}
): AdapterDelegate<D, DelegateViewHolder<D>> =
    object : AdapterDelegate<D, DelegateViewHolder<D>>() {
        override fun isForViewType(position: Int, data: D): Boolean =
            isForViewType(position, data)

        override fun onCreateViewHolder(parent: ViewGroup): DelegateViewHolder<D> {
            val itemView = createView(parent, layoutResId)

            return object : DslDelegateViewHolder<D, DT>(itemView) {
                override val item: DT?
                    get() = itemData as? DT
            }.also(block)
        }
    }

abstract class DslDelegateViewHolder<D, DT : D>(view: View) : DelegateViewHolder<D>(view) {
    private var onBindCallback: ((DT) -> Unit)? = null
    private var onUnbindCallback: (() -> Unit)? = null

    abstract val item: DT?

    fun onBind(onBindCallback: (DT) -> Unit) {
        if (this.onBindCallback != null) {
            throw IllegalStateException("onBind callback already set")
        }
        this.onBindCallback = onBindCallback
    }

    override fun onBind(data: D) {
        onBindCallback?.invoke(requireNotNull(item))
    }

    fun onUnbind(onUnbindCallback: () -> Unit) {
        if (this.onUnbindCallback != null) {
            throw IllegalStateException("onUnbind callback already set")
        }
        this.onUnbindCallback = onUnbindCallback
    }

    override fun onUnbind() {
        onUnbindCallback?.invoke()
    }
}