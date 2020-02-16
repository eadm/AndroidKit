package ru.nobird.android.presentation.base.delegate

import ru.nobird.android.presentation.base.DisposableViewModel
import ru.nobird.android.presentation.base.PresenterContract

abstract class PresenterDelegate<V> : PresenterContract<V>, DisposableViewModel() {
    override fun attachView(view: V) {}
    override fun detachView(view: V) {}
}