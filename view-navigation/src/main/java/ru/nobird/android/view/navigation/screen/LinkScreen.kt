package ru.nobird.android.view.navigation.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.terrakok.cicerone.android.support.SupportAppScreen

class LinkScreen(
    private val url: String
) : SupportAppScreen() {
    override fun getActivityIntent(context: Context?): Intent =
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}