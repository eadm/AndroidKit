package ru.nobird.android.view.redux.viewmodel

import androidx.lifecycle.ViewModel
import ru.nobird.app.presentation.redux.container.ReduxViewContainer
import ru.nobird.app.presentation.redux.container.ViewContainer

abstract class ReduxViewModel<State, Message, ViewAction>(
    private val viewContainer: ReduxViewContainer<State, Message, ViewAction>
) : ViewModel(), ViewContainer<State, Message, ViewAction> by viewContainer {
    override fun onCleared() {
        viewContainer.cancel()
    }
}
