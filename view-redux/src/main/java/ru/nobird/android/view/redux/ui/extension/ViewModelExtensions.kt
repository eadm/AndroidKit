package ru.nobird.android.view.redux.ui.extension

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
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
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                value.attachView(view)
            }

            override fun onStop(owner: LifecycleOwner) {
                value.detachView(view)
            }
        })
    }

    private var cached: VM? = null

    override val value: VM
        get() =
            cached ?: ViewModelProvider(viewModelStoreOwner.viewModelStore, factoryProducer())
                .get(viewModelClass.java)
                .also {
                    cached = it
                }

    override fun isInitialized(): Boolean =
        cached != null
}
