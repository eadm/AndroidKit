package ru.nobird.android.presentation.redux.dispatcher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

@Suppress("unused")
abstract class CoroutineActionDispatcher<Action, Message> : ActionDispatcher<Action, Message> {

    private val dispatcherScope = GlobalScope + Dispatchers.IO

    private var messageListener: ((Message) -> Unit)? = null

    protected fun background(block: suspend CoroutineScope.() -> Message) {
        dispatcherScope.launch {
            val resultMessage = block()
            if (isActive) {
                MainScope().launch {
                    // launch independent coroutine (without using of withContext())
                    // we do not want to cancel execution at this stage if dispatcherScope will become
                    // cancelled
                    onNewMessage(resultMessage)
                }
            }
        }
    }

    protected fun backgroundNoMessage(block: suspend CoroutineScope.() -> Unit) {
        dispatcherScope.launch(block = block)
    }

    override fun setListener(listener: (message: Message) -> Unit) {
        messageListener = listener
    }

    private fun onNewMessage(message: Message) {
        messageListener?.invoke(message)
    }

    override fun cancel() {
        dispatcherScope.cancel()
    }
}
