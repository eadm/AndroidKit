package ru.nobird.android.view.navigation.navigator

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import ru.nobird.android.view.navigation.navigator.command.Switch
import ru.nobird.android.view.navigation.ui.fragment.BackNavigationDelegate
import java.util.Stack

class RetainedAppNavigator(
    @IdRes
    private val containerId: Int,
    private val fragmentManager: FragmentManager,
    private val fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory,
    private val mode: Mode = Mode.ATTACH_DETACH,
    private val onScreenChanged: ((screenKey: String) -> Unit)? = null,
) : Navigator, BackNavigationDelegate {
    override val onBackPressedCallback =
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                applyCommands(arrayOf(Back()))
            }
        }

    val nestedNavigationHandlers: MutableSet<NestedNavigationHandler> =
        mutableSetOf()

    private val screensStack: Stack<String> = Stack()

    override fun applyCommands(commands: Array<out Command>) {
        fragmentManager.executePendingTransactions()
        commands.forEach(::applyCommand)
        invalidateOnBackPressedCallback()
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Switch -> switch(command)

            is Back -> back()

            else ->
                throw IllegalArgumentException("Unsupported command: $command")
        }
    }

    private fun switch(command: Switch) {
        val screen = command.screen

        val fragment = fragmentManager.findFragmentByTag(screen.screenKey)

        val isScreenOnTopOfStack =
            screensStack.isNotEmpty() && screensStack.peek() == screen.screenKey

        if (!isScreenOnTopOfStack) {
            screensStack.push(screen.screenKey)
        }

        val transaction = fragmentManager.beginTransaction()
        fragmentManager.fragments.forEach { transaction.deactivate(it) }
        if (fragment != null) {
            nestedNavigationHandlers.forEach { it.handleNestedNavigation(fragment, screen) }
            transaction.activate(fragment)
        } else {
            transaction.add(
                containerId,
                screen.createFragment(fragmentFactory),
                screen.screenKey
            )
        }
        transaction.commit()
        onScreenChanged?.invoke(screen.screenKey)
    }

    private fun back() {
        if (screensStack.size > 1) {
            screensStack.pop()

            val screenKey = screensStack.peek()

            val fragment = fragmentManager
                .findFragmentByTag(screenKey)
                ?: return

            val transaction = fragmentManager.beginTransaction()
            fragmentManager.fragments.forEach { transaction.deactivate(it) }
            transaction.activate(fragment)
            transaction.commit()

            onScreenChanged?.invoke(screenKey)
        }
    }

    override fun invalidateOnBackPressedCallback() {
        onBackPressedCallback.isEnabled = screensStack.size > 1
    }

    // region State
    fun saveState(): Bundle {
        val bundle = Bundle(SAVED_STATE_CAPACITY)
        bundle.putStringArray(KEY_SCREENS, screensStack.toTypedArray())
        return bundle
    }

    fun restoreState(savedState: Bundle) {
        val screens = savedState
            .getStringArray(KEY_SCREENS)
            .orEmpty()

        screensStack.clear()
        screensStack.addAll(screens)
        invalidateOnBackPressedCallback()
    }
    // endregion

    // region Mode extensions
    private fun FragmentTransaction.activate(fragment: Fragment) {
        when (mode) {
            Mode.SHOW_HIDE ->
                show(fragment)

            Mode.SHOW_HIDE_STARTED -> {
                show(fragment)
                setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
            }

            Mode.ATTACH_DETACH ->
                attach(fragment)
        }
    }

    private fun FragmentTransaction.deactivate(fragment: Fragment) {
        when (mode) {
            Mode.SHOW_HIDE ->
                hide(fragment)

            Mode.SHOW_HIDE_STARTED -> {
                hide(fragment)
                setMaxLifecycle(fragment, Lifecycle.State.STARTED)
            }

            Mode.ATTACH_DETACH ->
                detach(fragment)
        }
    }
    // endregion

    enum class Mode {
        /**
         * Uses FragmentTransaction::show and FragmentTransaction::hide.
         * No Lifecycle methods will be called.
         */
        SHOW_HIDE,

        /**
         * Uses FragmentTransaction::show and FragmentTransaction::hide.
         * currentFragment::onPause and nextFragment::onResume will be called.
         */
        SHOW_HIDE_STARTED,

        /**
         * Uses FragmentTransaction::attach and FragmentTransaction::detach.
         * currentFragment::onPause,::onStop,::onDestroyView and
         * nextFragment::onCreateView,::onStart,::onResume will be called.
         */
        ATTACH_DETACH
    }

    companion object {
        private const val SAVED_STATE_CAPACITY = 1
        private const val KEY_SCREENS = "screens"
    }
}
