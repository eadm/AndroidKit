package ru.nobird.android.presentation.redux.dispatcher

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

@Suppress("unused")
abstract class CoroutineActionDispatcher<Action, Message>(
    scopeConfig: ScopeConfigOptions
) : ActionDispatcher<Action, Message> {

    protected val backgroundScope: CoroutineScope = scopeConfig.createConfig().let {
        it.backgroundScope + SupervisorJob(it.backgroundScope.coroutineContext[Job]) + it.exceptionHandler
    }

    private var messageListener: ((Message) -> Unit)? = null

    /**
     * Default implementation of handleAction which uses [doActionInBackground] for all actions.
     * Override if you want to manage background/main-thread switches manually (see [background]).
     * Use [onNewMessage] to post result messages on main thread.
     */
    override fun handleAction(action: Action) {
        backgroundScope.launch {
            doActionInBackground(action)
        }
    }

    override fun setListener(listener: (message: Message) -> Unit) {
        messageListener = listener
    }

    override fun cancel() {
        backgroundScope.cancel()
        messageListener = null
    }

    /**
     * Executes specified [action] on Dispatchers.IO
     * The actions are cancellable by [cancel] call.
     * Override doActionInBackground when you want to handle all actions in background.
     * Use [onNewMessage] to post result messages on main thread.
     * If you want to manage background/main-thread coroutine switches manually see [handleAction].
     */
    protected open suspend fun doActionInBackground(action: Action) {
    }

    /**
     * The method exists for situations when you need to start suspendable code from non-suspendable
     * environment (mainly from custom [handleAction] implementation).
     * Executes specified [block] in background coroutine.
     * The coroutine is cancellable by [cancel] call.
     */
    protected fun background(block: suspend CoroutineScope.() -> Unit) {
        backgroundScope.launch(block = block)
    }

    /**
     * Posts specified [message] on main thread.
     */
    protected fun onNewMessage(message: Message) {
        MainScope().launch {
            // launch independent coroutine
            // we do not want to cancel execution at this stage if backgroundScope will become
            // cancelled
            messageListener?.invoke(message)
        }
    }

    interface ScopeConfigOptions {

        val backgroundScope: CoroutineScope?

        val exceptionHandler: CoroutineExceptionHandler?

        fun createConfig(): ScopeConfig =
            ScopeConfig(
                backgroundScope ?: GlobalScope + Dispatchers.IO,
                exceptionHandler
                    ?: backgroundScope?.coroutineContext?.get(CoroutineExceptionHandler)
                    ?: CoroutineExceptionHandler { _, throwable ->
                        // log error here
//                        Log.e(
//                            "CoroutineActionDispatch",
//                            "Unhandled exception in CoroutineActionDispatcher",
//                            throwable
//                        )
                    }
            )
    }

    data class ScopeConfig(
        val backgroundScope: CoroutineScope,
        val exceptionHandler: CoroutineExceptionHandler
    )
}
