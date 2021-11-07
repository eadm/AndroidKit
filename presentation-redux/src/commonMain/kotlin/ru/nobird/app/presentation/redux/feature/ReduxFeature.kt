package ru.nobird.app.presentation.redux.feature

import ru.nobird.app.presentation.redux.reducer.StateReducer

class ReduxFeature<State, Message, Action>(
    initialState: State,
    private val reducer: StateReducer<State, Message, Action>
) : Feature<State, Message, Action> {
    private val messageQueue = ArrayDeque<Message>()
    private var isFlushingMessages = false

    override var state: State = initialState
        private set

    private val stateListeners = mutableSetOf<(state: State) -> Unit>()
    private val actionListeners = mutableSetOf<(action: Action) -> Unit>()

    override fun onNewMessage(message: Message) {
        messageQueue.addLast(message)
        flushMessages()
    }

    private fun flushMessages() {
        if (isFlushingMessages) return

        isFlushingMessages = true
        while (messageQueue.isNotEmpty()) {
            val message = messageQueue.removeFirst()
            val (newState, actions) = reducer.reduce(state, message)
            state = newState

            stateListeners.notifyAll(newState)
            actions.forEach(actionListeners::notifyAll)
        }
        isFlushingMessages = false
    }

    override fun addStateListener(listener: (state: State) -> Unit) {
        stateListeners += listener
    }

    override fun addActionListener(listener: (state: Action) -> Unit) {
        actionListeners += listener
    }

    override fun cancel() {}
}
