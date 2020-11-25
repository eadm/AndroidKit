package ru.nobird.android.view.base.ui.extension

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.nobird.android.core.model.PaginationDirection

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
    if (!imm.hideSoftInputFromWindow(windowToken, 0)) {
        try {
            imm.hideSoftInputFromWindow(null, 0)
        } catch (_: Exception) {
            // no op
        }
    }
}

/**
 * Constant represents minimum percentage of keyboard size compared to screen height
 */
const val PART_OF_KEYBOARD_ON_SCREEN = 0.15

/**
 * Adds a listener to keyboard changed
 */
inline fun View.addKeyboardVisibilityListener(crossinline onKeyboardVisibilityChanged: (isVisible: Boolean) -> Unit) {
    val rect = Rect()
    viewTreeObserver.addOnGlobalLayoutListener {
        getWindowVisibleDisplayFrame(rect)

        val screenHeight = rootView.height
        val keyboardHeight = screenHeight - rect.bottom

        onKeyboardVisibilityChanged(keyboardHeight > screenHeight * PART_OF_KEYBOARD_ON_SCREEN)
    }
}

/**
 * Sets up pagination listener on current recycler view and returns it as RecyclerView.OnScrollListener
 */
fun RecyclerView.setOnPaginationListener(onPagination: (PaginationDirection) -> Unit): RecyclerView.OnScrollListener =
    object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val layoutManager = (recyclerView.layoutManager as? LinearLayoutManager)
                ?: return


            val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

            val delta = if (layoutManager.orientation == LinearLayoutManager.HORIZONTAL) {
                dx
            } else {
                dy
            }

            if (delta > 0) {
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    post { onPagination(PaginationDirection.NEXT) }
                }
            } else {
                if (pastVisibleItems == 0) {
                    post { onPagination(PaginationDirection.PREV) }
                }
            }
        }
    }.also(this::addOnScrollListener)