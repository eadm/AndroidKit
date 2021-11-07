package ru.nobird.android.view.base.ui.extension

import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun Snackbar.setTextColorRes(@ColorRes colorRes: Int): Snackbar =
    setTextColor(ContextCompat.getColor(context, colorRes))

fun Snackbar.setTextColor(@ColorInt textColor: Int): Snackbar =
    apply {
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .setTextColor(textColor)
    }