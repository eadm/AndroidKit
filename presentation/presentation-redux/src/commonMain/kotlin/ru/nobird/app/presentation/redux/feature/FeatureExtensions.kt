package ru.nobird.app.presentation.redux.feature

internal fun <T> Iterable<(T) -> Unit>.notifyAll(message: T) {
    forEach { it(message) }
}