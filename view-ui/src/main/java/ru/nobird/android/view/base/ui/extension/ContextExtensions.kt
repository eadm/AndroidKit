package ru.nobird.android.view.base.ui.extension

import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
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

/**
 * Converts current value in px to dp
 */
fun Int.toDp(): Int =
    this.toFloat().toDp().toInt()

/**
 * Converts current value in dp to px
 */
fun Int.toPx(): Int =
    this.toFloat().toPx().toInt()


/**
 * Converts current value in px to sp
 */
fun Int.toSp(): Int =
    this.toFloat().toSp().toInt()

/**
 * Converts current value in px to dp
 */
fun Float.toDp(): Float =
    this / Resources.getSystem().displayMetrics.density

/**
 * Converts current value in dp to px
 */
fun Float.toPx(): Float =
    this * Resources.getSystem().displayMetrics.density

/**
 * Converts current value in px to dp
 */
fun Float.toSp(): Float =
    this / Resources.getSystem().displayMetrics.scaledDensity


/**
 * True if MODE_NIGHT enabled
 */
fun Context.isNightModeEnabled(): Boolean =
    (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES