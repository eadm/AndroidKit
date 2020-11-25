package ru.nobird.android.presentation.redux.dispatcher

import ru.nobird.android.core.model.Cancellable

interface ActionDispatcher<Action, Message> : Cancellable {
    fun setListener(listener: (message: Message) -> Unit)
    fun handleAction(action: Action)
}
