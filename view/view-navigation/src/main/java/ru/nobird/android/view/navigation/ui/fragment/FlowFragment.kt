package ru.nobird.android.view.navigation.ui.fragment

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.nobird.android.view.navigation.ui.listener.BackButtonListener
import ru.nobird.android.view.navigation.ui.listener.BackNavigable

abstract class FlowFragment : Fragment(), BackButtonListener, BackNavigable {
    private val localCicerone: Cicerone<Router> = Cicerone.create()

    protected abstract val navigator: Navigator
    protected abstract val containerId: Int

    val router: Router = localCicerone.router

    override fun onResume() {
        super.onResume()
        localCicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        localCicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        (childFragmentManager.findFragmentById(containerId) as? BackButtonListener)
            ?.onBackPressed()
            ?.takeIf { it }
            ?: router.exit()
        return true
    }

    override fun onNavigateBack() {
        router.exit()
    }

    open fun resetNavigation() {}
}