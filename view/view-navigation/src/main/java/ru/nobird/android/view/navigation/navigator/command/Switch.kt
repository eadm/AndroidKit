package ru.nobird.android.view.navigation.navigator.command

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Switches screen in RetainedAppNavigator for [screen].
 * If [screen] is already on top of the stack and [resetNavigation] is true
 * then resetNavigation method of ResettableNavigationContainer will be called.
 */
data class Switch(
    val screen: FragmentScreen,
    val resetNavigation: Boolean = false
) : Command