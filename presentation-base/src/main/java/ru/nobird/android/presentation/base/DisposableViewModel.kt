package ru.nobird.android.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class DisposableViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    protected val nestedDisposables: MutableList<DisposableViewModel> = arrayListOf()

    @CallSuper
    override fun onCleared() {
        nestedDisposables.forEach { it.onCleared() }
        compositeDisposable.dispose()
    }

    @CallSuper
    open fun onSaveInstanceState(outState: Bundle) {
        nestedDisposables.forEachIndexed { index, nestedPresenter ->
            val bundle = Bundle()
            nestedPresenter.onSaveInstanceState(bundle)

            outState.putBundle("${nestedPresenter::class.java.canonicalName}:$index", bundle)
        }
    }

    @CallSuper
    open fun onRestoreInstanceState(savedInstanceState: Bundle) {
        nestedDisposables.forEachIndexed { index, nestedPresenter ->
            savedInstanceState
                .getBundle("${nestedPresenter::class.java.canonicalName}:$index")
                ?.let(nestedPresenter::onRestoreInstanceState)
        }
    }
}