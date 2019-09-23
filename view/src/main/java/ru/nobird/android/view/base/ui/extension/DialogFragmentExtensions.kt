package ru.nobird.android.view.base.ui.extension

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.showIfNotExists(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null) {
        show(manager, tag)
    }
}