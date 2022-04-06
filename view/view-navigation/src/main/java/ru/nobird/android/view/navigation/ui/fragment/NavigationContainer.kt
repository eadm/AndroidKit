package ru.nobird.android.view.navigation.ui.fragment

import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router

interface NavigationContainer {
    val router: Router
    val navigator: Navigator
}