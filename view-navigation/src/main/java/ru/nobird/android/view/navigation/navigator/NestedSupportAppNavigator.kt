package ru.nobird.android.view.navigation.navigator

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.nobird.android.view.navigation.ui.listener.BackNavigable
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class NestedSupportAppNavigator(
    private val activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {
    override fun activityBack() {
        if (activity is BackNavigable) {
            activity.onNavigateBack()
        } else {
            super.activityBack()
        }
    }
}
