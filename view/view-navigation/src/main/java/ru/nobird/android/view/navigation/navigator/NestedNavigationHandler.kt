package ru.nobird.android.view.navigation.navigator

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface NestedNavigationHandler {
    /**
     * Will be called in case if [fragment]
     * already exists to pass new arguments from [screen]
     */
    fun handleNestedNavigation(fragment: Fragment, screen: FragmentScreen)
}