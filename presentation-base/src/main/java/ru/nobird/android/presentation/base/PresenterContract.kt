package ru.nobird.android.presentation.base

interface PresenterContract<V> {
    fun attachView(view: V)
    fun detachView(view: V)
}