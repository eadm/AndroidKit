package ru.nobird.android.view.base.ui.extension

import android.app.ActivityManager
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
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

/**
 * Resolve attrs
 */
fun Context.resolveAttribute(@AttrRes attributeResId: Int): TypedValue? {
    val typedValue = TypedValue()
    return if (theme.resolveAttribute(attributeResId, typedValue, true)) {
        typedValue
    } else {
        null
    }
}

/**
 * Resolve color atrrs
 * */
@ColorInt
fun Context.resolveColorAttribute(@AttrRes attributeResId: Int): Int =
    resolveAttribute(attributeResId)?.data ?: 0

/**
 * Resolve float atrrs
 * */
fun Context.resolveFloatAttribute(@AttrRes attributeResId: Int): Float =
    resolveAttribute(attributeResId)?.float ?: 0f

/**
 * Resolve resource atrrs
 * */
fun Context.resolveResourceIdAttribute(@AttrRes attributeResId: Int): Int =
    resolveAttribute(attributeResId)?.resourceId ?: 0

/**
 * Resolve dimentions
 * */
fun Context.resolveDimension(@AttrRes attributeResId: Int): Float =
    resolveAttribute(attributeResId)
        ?.getDimension(resources.displayMetrics) ?: 0f
