package ru.nobird.android.presentation.redux.container

interface ViewContainer<State, Message, ViewAction> {
    fun attachView(view: ReduxView<State, ViewAction>)
    fun detachView(view: ReduxView<State, ViewAction>)

    fun onNewMessage(message: Message)
}
