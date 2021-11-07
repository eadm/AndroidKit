package ru.nobird.android.presentation.base

import androidx.annotation.CallSuper

class DefaultPresenterViewContainer<V> : PresenterViewContainer<V> {
    @Volatile
    override var view: V? = null
        private set

    @CallSuper
    override fun attachView(view: V) {
        val previousView = this.view

        check(previousView == null) { "Previous view is not detached! previousView = $previousView" }

        this.view = view
    }

    @CallSuper
    override fun detachView(view: V) {
        val previousView = this.view

        check(previousView === view) { "Unexpected view! previousView = $previousView, getView to unbind = $view" }

        this.view = null
    }
}