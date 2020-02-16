package ru.nobird.android.presentation.base.action

import ru.nobird.android.presentation.base.DisposableViewModel
import ru.nobird.android.presentation.base.PresenterContract

abstract class PresenterAction<V> : PresenterContract<V>, DisposableViewModel() {
    override fun attachView(view: V) {}
    override fun detachView(view: V) {}
}