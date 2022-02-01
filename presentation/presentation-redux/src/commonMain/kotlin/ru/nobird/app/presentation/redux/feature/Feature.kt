package ru.nobird.app.presentation.redux.feature

import ru.nobird.app.core.model.Cancellable

interface Feature<State, Message, Action> : Cancellable {
    val state: State
    fun onNewMessage(message: Message)

    fun addStateListener(listener: (state: State) -> Unit): Cancellable
    fun addActionListener(listener: (action: Action) -> Unit): Cancellable
}
