package ru.nobird.android.view.navigation.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen

class LinkScreen(
    private val url: String
) : ActivityScreen {
    override fun createIntent(context: Context): Intent =
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}