package ru.nobird.android.view.navigation.navigator

import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nobird.android.view.navigation.ui.fragment.FlowFragment
import java.util.Stack

class RetainedAppNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    @IdRes
    containerId: Int,
    private val onScreenChanged: ((screenKey: String) -> Unit)? = null,
    private val animations: Array<Int> = emptyArray()
) : AppNavigator(activity, containerId, fragmentManager) {
    private val screensStack: Stack<String> = Stack()

    val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            isEnabled = screensStack.size > 1
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
            }
        }
    }

    override fun applyCommands(commands: Array<out Command>) {
        fragmentManager.executePendingTransactions()
        commands.forEach(::applyCommand)
    }

    override fun applyCommand(command: Command) {
        when (command) {
            is Replace -> {
                val screen = command.screen as? FragmentScreen
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
                    transaction.add(
                        containerId,
                        screen.createFragment(fragmentFactory),
                        screen.screenKey
                    )
                }
                transaction.commit()
                onScreenChanged?.invoke(screen.screenKey)
            }

            is Back -> {
                onBackPressedCallback.handleOnBackPressed()
            }
        }
    }

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        super.setupFragmentTransaction(screen, fragmentTransaction.apply {
            when(animations.size) {
                2 -> setCustomAnimations(animations.first(), animations.last())
                4 -> setCustomAnimations(animations[0], animations[1], animations[2], animations[3])
            }
        }, currentFragment, nextFragment)
    }
}
