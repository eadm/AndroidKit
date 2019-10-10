package ru.nobird.android.view.navigation.navigator

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import ru.nobird.android.view.navigation.ui.fragment.FlowFragment
import java.util.Stack

class RetainedAppNavigator(
    private val fragmentManager: FragmentManager,
    @IdRes
    private val containerId: Int,

    private val onScreenChanged: ((screenKey: String) -> Unit)? = null,
    private val onNeedNavigateBack: (() -> Unit)? = null
) : Navigator {
    private val screensStack: Stack<String> = Stack()

    override fun applyCommands(commands: Array<out Command>?) {
        fragmentManager.executePendingTransactions()
        commands?.forEach(::applyCommand)
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Replace -> {
                val screen = command.screen as? SupportAppScreen
                    ?: return

                val fragment = fragmentManager.findFragmentByTag(screen.screenKey)

                if (screensStack.isNotEmpty() && screensStack.peek() == screen.screenKey) {
                    (fragment as? FlowFragment)
                        ?.resetNavigation()
                } else {
                    screensStack.push(screen.screenKey)
                }

                val transaction = fragmentManager.beginTransaction()
                fragmentManager.fragments.forEach { transaction.hide(it) }
                if (fragment != null) {
                    transaction.show(fragment)
                } else {
                    transaction.add(containerId, screen.fragment, screen.screenKey)
                }
                transaction.commit()
                onScreenChanged?.invoke(screen.screenKey)
            }

            is Back -> {
                popBackStack()
            }
        }
    }

    private fun popBackStack() {
        if (screensStack.size > 1) {
            screensStack.pop()

            val screenKey = screensStack.peek()

            val fragment = fragmentManager
                .findFragmentByTag(screenKey)
                ?: return

            val transaction = fragmentManager.beginTransaction()
            fragmentManager.fragments.forEach { transaction.hide(it) }
            transaction.show(fragment)
            transaction.commit()

            onScreenChanged?.invoke(screenKey)
        } else {
            // exit
            onNeedNavigateBack?.invoke()
        }
    }
}