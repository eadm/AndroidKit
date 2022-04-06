package ru.nobird.android.view.navigation.router

import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.BaseRouter
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nobird.android.view.navigation.navigator.command.Switch

class RetainedRouter : BaseRouter() {
    fun switch(screen: FragmentScreen, resetNavigation: Boolean = false) {
        executeCommands(Switch(screen, resetNavigation))
    }

    fun back() {
        executeCommands(Back())
    }
}