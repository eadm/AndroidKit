package ru.nobird.app.presentation.redux.dispatcher

import ru.nobird.app.presentation.redux.feature.Feature

fun <State, Message, Action> Feature<State, Message, Action>.wrapWithActionDispatcher(
    dispatcher: ActionDispatcher<Action, Message>
): Feature<State, Message, Action> =
    object : Feature<State, Message, Action> by this {
        override fun cancel() {
            dispatcher.cancel()
            this@wrapWithActionDispatcher.cancel()
        }
    }.apply {
        dispatcher.setListener(::onNewMessage)
        addActionListener(dispatcher::handleAction)
    }

fun <Action1, Action2, Message1, Message2> ActionDispatcher<Action1, Message1>.transform(
    transformAction: (Action2) -> Action1?,
    transformMessage: (Message1) -> Message2?
) : ActionDispatcher<Action2, Message2> =
    object : ActionDispatcher<Action2, Message2> {
        override fun setListener(listener: (message: Message2) -> Unit) {
            this@transform.setListener { transformMessage(it)?.let(listener) }
        }

        override fun handleAction(action: Action2) {
            transformAction(action)?.let(this@transform::handleAction)
        }

        override fun cancel() {
            this@transform.cancel()
        }
    }