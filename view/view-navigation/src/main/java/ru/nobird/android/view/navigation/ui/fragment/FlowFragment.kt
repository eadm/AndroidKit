package ru.nobird.android.view.navigation.ui.fragment

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router

abstract class FlowFragment : Fragment(), NavigationContainer{
    final override val localCicerone: Cicerone<Router> = Cicerone.create()

    abstract override val navigator: Navigator
    abstract override val containerId: Int

    override val router: Router = localCicerone.router

    override fun onResume() {
        super.onResume()
        localCicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        localCicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun resetNavigation() {}
}