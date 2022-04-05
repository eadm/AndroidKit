package ru.nobird.android.view.base.ui.extension

import android.content.res.Resources

/**
 * Inline class represents value in Dimension Pixels
 */
@JvmInline
value class Dp(val value: Float)

/**
 * Converts Int to Dp
 */
val Int.dp: Dp
    get() = Dp(this.toFloat())

/**
 * Converts Float to Dp
 */
val Float.dp: Dp
    get() = Dp(this)

/**
 * Converts Dp to Px
 */
fun Dp.toPx(): Px =
    Px(value * Resources.getSystem().displayMetrics.density)

/**
 * Converts Dp to Float
 */
fun Dp.toFloat(): Float =
    value

/**
 * Converts Dp to Int
 */
fun Dp.toInt(): Int =
    value.toInt()


/**
 * Inline class represents value in Scaled Pixels
 */
@JvmInline
value class Sp(val value: Float)

/**
 * Converts Int to Sp
 */
val Int.sp: Sp
    get() = Sp(this.toFloat())

/**
 * Converts Float to Sp
 */
val Float.sp: Sp
    get() = Sp(this)

/**
 * Converts Sp to Px
 */
fun Sp.toPx(): Px =
    Px(value * Resources.getSystem().displayMetrics.scaledDensity)

/**
 * Converts Sp to Float
 */
fun Sp.toFloat(): Float =
    value

/**
 * Converts Sp to Int
 */
fun Sp.toInt(): Int =
    value.toInt()


/**
 * Inline class represents value in Pixels
 */
@JvmInline
value class Px(val value: Float)

/**
 * Converts Int to Px
 */
val Int.px: Px
    get() = Px(this.toFloat())

/**
 * Converts Float to Px
 */
val Float.px: Px
    get() = Px(this)

/**
 * Converts Px to Dp
 */
fun Px.toDp(): Dp =
    Dp(value / Resources.getSystem().displayMetrics.density)

/**
 * Converts Px to Sp
 */
fun Px.toSp(): Sp =
    Sp(value / Resources.getSystem().displayMetrics.scaledDensity)

/**
 * Converts Px to Float
 */
fun Px.toFloat(): Float =
    value

/**
 * Converts Px to Int
 */
fun Px.toInt(): Int =
    value.toInt()

/**
 * Converts current value in px to dp
 */
@Deprecated(
    message = "Use inline class based solution for better readability",
    replaceWith = ReplaceWith(expression = "this.px.toDp().toInt()")
)
fun Int.toDp(): Int =
    this.toFloat().toDp().toInt()

/**
 * Converts current value in dp to px
 */
@Deprecated(
    message = "Use inline class based solution for better readability",
    replaceWith = ReplaceWith(expression = "this.dp.toPx().toInt()")
)
fun Int.toPx(): Int =
    this.toFloat().toPx().toInt()


/**
 * Converts current value in px to sp
 */
@Deprecated(
    message = "Use inline class based solution for better readability",
    replaceWith = ReplaceWith(expression = "this.px.toSp().toInt()")
)
fun Int.toSp(): Int =
    this.toFloat().toSp().toInt()

/**
 * Converts current value in px to dp
 */
@Deprecated(
    message = "Use inline class based solution for better readability",
    replaceWith = ReplaceWith(expression = "this.px.toDp().toFloat()")
)
fun Float.toDp(): Float =
    this / Resources.getSystem().displayMetrics.density

/**
 * Converts current value in dp to px
 */
@Deprecated(
    message = "Use inline class based solution for better readability",
    replaceWith = ReplaceWith(expression = "this.dp.toPx().toFloat()")
)
fun Float.toPx(): Float =
    this * Resources.getSystem().displayMetrics.density

/**
 * Converts current value in px to sp
 */
@Deprecated(
    message = "Use inline class based solution for better readability",
    replaceWith = ReplaceWith(expression = "this.px.toSp().toFloat()")
)
fun Float.toSp(): Float =
    this / Resources.getSystem().displayMetrics.scaledDensity
