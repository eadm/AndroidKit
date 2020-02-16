package ru.nobird.android.presentation.base

import androidx.annotation.CallSuper
import ru.nobird.android.presentation.base.action.PresenterAction

abstract class PresenterBase<V>(
    private val presenterViewContainer: PresenterViewContainer<V> = DefaultPresenterViewContainer()
) : DisposableViewModel(), PresenterViewContainer<V> by presenterViewContainer {
    protected val actions: MutableList<PresenterAction<V>> = arrayListOf()

    @CallSuper
    override fun attachView(view: V) {
        presenterViewContainer.attachView(view)
        actions.forEach { it.attachView(view) }
    }

    @CallSuper
    override fun detachView(view: V) {
        actions.forEach { it.detachView(view) }
        presenterViewContainer.detachView(view)
    }
}
