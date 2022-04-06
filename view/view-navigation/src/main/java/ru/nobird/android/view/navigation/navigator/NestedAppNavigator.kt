package ru.nobird.android.view.navigation.navigator

import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nobird.android.view.navigation.ui.fragment.BackNavigationDelegate

class NestedAppNavigator(
    activity: FragmentActivity,
    @IdRes containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory
) : AppNavigator(activity, containerId, fragmentManager, fragmentFactory),
    BackNavigationDelegate {
    val interceptors = mutableSetOf<FragmentTransactionInterceptor>()

    override val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                applyCommands(arrayOf(Back()))
            }
        }

    init {
        fragmentManager.addOnBackStackChangedListener {
            invalidateOnBackPressedCallback()
        }
    }

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        interceptors.forEach {
            it.setupFragmentTransaction(screen, fragmentTransaction, currentFragment, nextFragment)
        }
    }

    override fun invalidateOnBackPressedCallback() {
        onBackPressedCallback.isEnabled =
            fragmentManager.backStackEntryCount > 0
    }

    override fun activityBack() {
        activity.onBackPressed()
    }
}
