package ru.nobird.android.view.base.ui.extension

import android.app.ActivityManager
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

/**
 * Returns true if current process is app's main process
 */
val Context.isMainProcess: Boolean
    get() {
        val pid = android.os.Process.myPid()
        return (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)
            ?.runningAppProcesses
            ?.find { it.pid == pid }
            ?.processName
            ?.let { it.isNotEmpty() && it == packageName }
            ?: false
    }

/**
 * Returns drawable for [drawableRes]
 */
fun Context.getDrawableCompat(@DrawableRes drawableRes: Int): Drawable =
    AppCompatResources.getDrawable(this, drawableRes) as Drawable

