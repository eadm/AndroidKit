package ru.nobird.android.presentation.redux.feature

import ru.nobird.android.core.model.Cancellable

interface Feature<State, Message, Action> : Cancellable {
    val state: State
    fun onNewMessage(message: Message)

    fun addStateListener(listener: (state: State) -> Unit)
    fun addActionListener(listener: (action: Action) -> Unit)
}
