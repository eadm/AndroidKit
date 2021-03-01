package ru.nobird.android.view.base.ui.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

/**
 * Convert drawable to bitmap
 * */
fun Drawable.toBitmap(width: Int = intrinsicWidth, height: Int = intrinsicHeight): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}