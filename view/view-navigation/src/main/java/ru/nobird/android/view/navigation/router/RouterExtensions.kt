package ru.nobird.android.view.navigation.router

import com.github.terrakok.cicerone.ResultListener
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Router.observeResult(resultKey: String): Flow<Any> =
    callbackFlow {
        val router = this@observeResult
        val channel = this

        val resultListener = FlowResultListener(router, resultKey, channel)

        awaitClose { resultListener.dispose() }
    }

private class FlowResultListener(
    private val router: Router,
    private val resultKey: String,
    private val channel: SendChannel<Any>
) : ResultListener {
    private var resultListenerHandler = router.setResultListener(resultKey, this)

    override fun onResult(data: Any) {
        channel.offer(data)
        resultListenerHandler = router.setResultListener(resultKey, this)
    }

    fun dispose() {
        resultListenerHandler.dispose()
    }
}
