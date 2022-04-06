package ru.nobird.android.view.navigation.ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import ru.nobird.android.view.navigation.R
import ru.nobird.android.view.navigation.navigator.NestedAppNavigator

abstract class FlowFragment(
    @LayoutRes contentLayoutId: Int = R.layout.fragment_navigation_container
) : Fragment(contentLayoutId),
    NavigationContainer {

    private val localCicerone: Cicerone<Router> = Cicerone.create()

    override val router: Router = localCicerone.router

    override val navigator: Navigator by lazy(LazyThreadSafetyMode.NONE) {
        NestedAppNavigator(
            requireActivity(),
            R.id.navigation_container,
            childFragmentManager
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backNavigationDelegate = navigator as? BackNavigationDelegate
        if (backNavigationDelegate != null) {
            addBackNavigationDelegate(backNavigationDelegate)
        }
    }

    override fun onResume() {
        super.onResume()
        localCicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        localCicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }
}