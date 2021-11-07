package ru.nobird.app.presentation.redux.container

import ru.nobird.app.core.model.Cancellable
import ru.nobird.app.presentation.redux.feature.Feature

class ReduxViewContainer<State, Message, ViewAction>(
    private val feature: Feature<State, Message, ViewAction>
) : ViewContainer<State, Message, ViewAction>, Cancellable {
    init {
        feature.addStateListener(this::state::set)
        feature.addActionListener(::handleAction)
    }

    private var view: ReduxView<State, ViewAction>? = null

    private var state: State
        get() = feature.state
        set(value) {
            view?.render(value)
        }

    private val viewActionQueue = ArrayDeque<ViewAction>()

    override fun onNewMessage(message: Message) {
        feature.onNewMessage(message)
    }

    private fun handleAction(action: ViewAction) {
        view?.onAction(action) ?: viewActionQueue.addLast(action)
    }

    override fun attachView(view: ReduxView<State, ViewAction>) {
        val previousView = this.view

        check(previousView == null) { "Previous view is not detached! previousView = $previousView" }

        this.view = view

        view.render(state)
        while (viewActionQueue.isNotEmpty()) {
            view.onAction(viewActionQueue.removeFirst())
        }
    }

    override fun detachView(view: ReduxView<State, ViewAction>) {
        val previousView = this.view

        check(previousView === view) { "Unexpected view! previousView = $previousView, getView to unbind = $view" }

        this.view = null
    }

    override fun cancel() {
        feature.cancel()
    }
}
