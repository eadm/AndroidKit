package ru.nobird.android.view.base.ui.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/**
 * Shows snackbar at current view
 */
inline fun View.snackbar(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_SHORT, action: Snackbar.() -> Unit = {}) {
    snackbar(context.getString(messageRes), length, action)
}

/**
 * Shows snackbar at current view
 */
inline fun View.snackbar(message: String, length: Int = Snackbar.LENGTH_SHORT, action: Snackbar.() -> Unit = {}) {
    Snackbar
        .make(this, message, length)
        .apply(action)
        .show()
}

/**
 * Hide keyboard
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}