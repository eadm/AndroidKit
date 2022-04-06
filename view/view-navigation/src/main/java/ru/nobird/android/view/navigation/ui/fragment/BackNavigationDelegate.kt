package ru.nobird.android.view.navigation.ui.fragment

import androidx.activity.OnBackPressedCallback

interface BackNavigationDelegate {
    val onBackPressedCallback: OnBackPressedCallback

    fun invalidateOnBackPressedCallback()
}