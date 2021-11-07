package ru.nobird.app.presentation.redux.container

interface ReduxView<State, ViewAction> {
    fun render(state: State)
    fun onAction(action: ViewAction)
}
