package ru.nobird.android.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class PresenterBase<V> : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    @Volatile
    var view: V? = null
        private set

    @CallSuper
    open fun attachView(view: V) {
        val previousView = this.view

        check(previousView == null) { "Previous view is not detached! previousView = $previousView" }

        this.view = view
    }

    @CallSuper
    open fun detachView(view: V) {
        val previousView = this.view

        if (previousView === view) {
            this.view = null
        } else {
            throw IllegalStateException("Unexpected view! previousView = $previousView, getView to unbind = $view")
        }
    }

    @CallSuper
    override fun onCleared() {
        compositeDisposable.dispose()
    }

    open fun onSaveInstanceState(outState: Bundle) {}
    open fun onRestoreInstanceState(savedInstanceState: Bundle) {}
}
