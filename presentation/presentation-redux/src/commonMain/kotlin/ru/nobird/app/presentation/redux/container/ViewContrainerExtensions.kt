package ru.nobird.app.presentation.redux.container

import ru.nobird.app.core.model.Cancellable
import ru.nobird.app.presentation.redux.feature.Feature

inline fun <State, Message, Action, reified ViewAction : Action> Feature<State, Message, Action>.wrapWithViewContainer(): ReduxViewContainer<State, Message, ViewAction> {
    val feature = object : Feature<State, Message, ViewAction> {
        override val state: State
            get() = this@wrapWithViewContainer.state

        override fun addActionListener(listener: (action: ViewAction) -> Unit): Cancellable =
            this@wrapWithViewContainer.addActionListener { (it as? ViewAction)?.let(listener) }

        override fun addStateListener(listener: (state: State) -> Unit): Cancellable =
            this@wrapWithViewContainer.addStateListener(listener)

        override fun onNewMessage(message: Message) {
            this@wrapWithViewContainer.onNewMessage(message)
        }

        override fun cancel() {
            this@wrapWithViewContainer.cancel()
        }
    }

    return ReduxViewContainer(feature)
}
