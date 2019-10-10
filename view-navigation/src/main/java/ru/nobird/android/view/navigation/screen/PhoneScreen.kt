package ru.nobird.android.view.navigation.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.terrakok.cicerone.android.support.SupportAppScreen

class PhoneScreen(
    private val phoneNumber: String
) : SupportAppScreen() {
    override fun getActivityIntent(context: Context?): Intent =
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
}