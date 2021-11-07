package ru.nobird.android.view.redux.ui.extension

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import ru.nobird.android.presentation.redux.container.ReduxView
import ru.nobird.android.view.redux.viewmodel.ReduxViewModel
import kotlin.reflect.KClass

@Suppress("unused")
inline fun <F, State, Message, Action, reified VM : ReduxViewModel<State, Message, Action>> F.reduxViewModel(
    view: ReduxView<State, Action>,
    noinline factoryProducer: () -> ViewModelProvider.Factory
): Lazy<VM> where F : LifecycleOwner, F : ViewModelStoreOwner =
    ReduxViewModelLazy(lifecycle, view, VM::class, viewModelStoreOwner = this, factoryProducer)

class ReduxViewModelLazy<State, Message, Action, VM : ReduxViewModel<State, Message, Action>>(
    lifecycle: Lifecycle,
    view: ReduxView<State, Action>,
    private val viewModelClass: KClass<VM>,
    private val viewModelStoreOwner: ViewModelStoreOwner,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<VM> {
    init {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_START ->
                        value.attachView(view)

                    Lifecycle.Event.ON_STOP ->
                        value.detachView(view)

                    else -> {
                        // no op
                    }
                }
            }
        })
    }

    private var cached: VM? = null

    override val value: VM
        get() =
            cached ?: ViewModelProvider(viewModelStoreOwner.viewModelStore, factoryProducer())[viewModelClass.java]
                .also {
                    cached = it
                }

    override fun isInitialized(): Boolean =
        cached != null
}
