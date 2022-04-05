package ru.nobird.android.view.navigation.ui.listener

interface BackNavigable {
    /**
     * Called when nested navigation cannot navigate back
     */
    fun onNavigateBack()
}