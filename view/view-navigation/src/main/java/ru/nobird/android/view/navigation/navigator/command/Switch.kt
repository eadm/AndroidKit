package ru.nobird.android.view.navigation.navigator.command

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 * Switches screen in RetainedAppNavigator for [screen].
 */
data class Switch(
    val screen: FragmentScreen
) : Command