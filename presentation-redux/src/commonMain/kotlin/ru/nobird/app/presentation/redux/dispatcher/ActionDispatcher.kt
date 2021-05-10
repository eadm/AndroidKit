package ru.nobird.app.presentation.redux.dispatcher

import ru.nobird.app.core.model.Cancellable

interface ActionDispatcher<Action, Message> : Cancellable {
    fun setListener(listener: (message: Message) -> Unit)
    fun handleAction(action: Action)
}
