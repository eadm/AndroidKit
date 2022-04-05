package ru.nobird.android.view.injection.base

abstract class SingleComponentHolder<out Component> : ComponentHolder<Component> {
    private var pair: Pair<Component, Int>? = null

    @Synchronized
    override fun provideComponent(): Component {
        val localPair = pair
        val component: Component
        if (localPair == null) {
            component = provideInternal()
            pair = Pair(component, 1)
        } else {
            component = localPair.first
            pair = Pair(component, localPair.second + 1)
        }
        return component
    }

    protected abstract fun provideInternal(): Component

    @Synchronized
    override fun releaseComponent() {
        val localPair = pair ?: return

        pair =
            if (localPair.second == 1) {
                null
            } else {
                Pair(localPair.first, localPair.second - 1)
            }
    }
}