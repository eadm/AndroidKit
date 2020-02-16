package ru.nobird.android.presentation.base

import androidx.annotation.CallSuper
import ru.nobird.android.presentation.base.delegate.PresenterDelegate

abstract class PresenterBase<V>(
    private val presenterViewContainer: PresenterViewContainer<V> = DefaultPresenterViewContainer()
) : DisposableViewModel(), PresenterViewContainer<V> by presenterViewContainer {
    protected val delegates: MutableList<PresenterDelegate<V>> = arrayListOf()

    @CallSuper
    override fun attachView(view: V) {
        presenterViewContainer.attachView(view)
        delegates.forEach { it.attachView(view) }
    }

    @CallSuper
    override fun detachView(view: V) {
        delegates.forEach { it.detachView(view) }
        presenterViewContainer.detachView(view)
    }
}
