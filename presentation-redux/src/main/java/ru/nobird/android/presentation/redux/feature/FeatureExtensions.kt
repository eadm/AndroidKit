package ru.nobird.android.presentation.redux.feature

internal fun <T> Iterable<(T) -> Unit>.notifyAll(message: T) {
    forEach { it(message) }
}