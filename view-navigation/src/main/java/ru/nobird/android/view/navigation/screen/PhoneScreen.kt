package ru.nobird.android.view.navigation.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen

class PhoneScreen(
    private val phoneNumber: String
) : ActivityScreen {
    override fun createIntent(context: Context): Intent =
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
}