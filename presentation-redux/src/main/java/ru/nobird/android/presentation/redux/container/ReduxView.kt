package ru.nobird.android.presentation.redux.container

interface ReduxView<State, ViewAction> {
    fun render(state: State)
    fun onAction(action: ViewAction)
}
