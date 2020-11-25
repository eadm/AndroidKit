package ru.nobird.android.presentation.redux.dispatcher

import io.reactivex.disposables.CompositeDisposable

@Suppress("unused")
abstract class RxActionDispatcher<Action, Message> : ActionDispatcher<Action, Message> {
    private var messageListener: ((Message) -> Unit)? = null

    protected val compositeDisposable = CompositeDisposable()

    override fun setListener(listener: (message: Message) -> Unit) {
        messageListener = listener
    }

    protected fun onNewMessage(message: Message) {
        messageListener?.invoke(message)
    }

    override fun cancel() {
        compositeDisposable.clear()
    }
}
