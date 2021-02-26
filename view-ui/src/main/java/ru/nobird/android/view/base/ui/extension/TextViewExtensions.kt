import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

/**
 * For compound drawables
 * */
internal fun TextView.setCompoundDrawables(
        @DrawableRes start: Int = -1,
        @DrawableRes top: Int = -1,
        @DrawableRes end: Int = -1,
        @DrawableRes bottom: Int = -1
) {
    val startDrawable = getDrawableOrNull(start)
    val topDrawable = getDrawableOrNull(top)
    val endDrawable = getDrawableOrNull(end)
    val bottomDrawable = getDrawableOrNull(bottom)
    setCompoundDrawablesWithIntrinsicBounds(startDrawable, topDrawable, endDrawable, bottomDrawable)
}

private fun TextView.getDrawableOrNull(@DrawableRes res: Int) =
        if (res != -1) AppCompatResources.getDrawable(context, res) else null