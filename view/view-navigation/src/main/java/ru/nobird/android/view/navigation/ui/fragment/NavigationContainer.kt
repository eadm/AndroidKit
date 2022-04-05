package ru.nobird.android.view.navigation.ui.fragment

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router

interface NavigationContainer {
    val localCicerone: Cicerone<Router>
    val router: Router
    val navigator: Navigator
    val containerId: Int

    fun resetNavigation();
}