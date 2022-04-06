package ru.nobird.android.view.navigation.ui.fragment

import androidx.fragment.app.Fragment

fun Fragment.addBackNavigationDelegate(delegate: BackNavigationDelegate) {
    delegate.invalidateOnBackPressedCallback()
    requireActivity().onBackPressedDispatcher
        .addCallback(this, delegate.onBackPressedCallback)
}