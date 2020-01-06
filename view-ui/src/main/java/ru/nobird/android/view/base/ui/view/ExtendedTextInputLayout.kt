package ru.nobird.android.view.base.ui.view

import android.animation.ValueAnimator
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.LinearInterpolator
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class ExtendedTextInputLayout
@JvmOverloads
constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr),
    ViewTreeObserver.OnPreDrawListener {

    companion object {
        private const val ANIMATION_DURATION_MS = 200L
    }

    private lateinit var hintView: TextView
    private lateinit var textView: TextView

    var hint: CharSequence?
        get() = hintView.text
        set(value) {
            hintView.text = value
        }

    var text: CharSequence?
        get() = textView.text
        set(value) {
            textView.text = value
        }

    var isHintEnabled: Boolean
        get() = hintView.visibility == View.VISIBLE
        set(value) {
            hintView.visibility =
                if (value) View.VISIBLE else View.GONE
        }

    private var hintCollapsedTextSize: Float = 0f
    private var hintExpandedTextSize: Float = 0f

    private var isHintExpanded = false
    private var hintAnimationFraction: Float = 0f
        set(value) {
            field = value

            hintView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (hintExpandedTextSize - hintCollapsedTextSize) * value + hintCollapsedTextSize)
            hintView.translationY = ((height - paddingTop - paddingBottom - ((textView.minLines.takeIf { it > 0 } ?: 1) - 1) * textView.lineHeight) - hintView.height) * value / 2
        }

    private var hintAnimator: ValueAnimator? = null

    private var isInDrawableStateChange = false

    init {
        setWillNotDraw(false)
        setAddStatesFromChildren(true)
        orientation = VERTICAL

        viewTreeObserver.addOnPreDrawListener(this)
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if (child is TextView) {
            when {
                !this::hintView.isInitialized -> {
                    hintView = child
                    hintCollapsedTextSize = hintView.textSize
                }

                !this::textView.isInitialized -> {
                    textView = child
                    hintExpandedTextSize = textView.textSize
                    textView.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                            updateLabelState(animate = false)
                            viewTreeObserver.addOnPreDrawListener(this@ExtendedTextInputLayout)
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    })

                    updateLabelState(animate = false)
                }
            }
        }
        super.addView(child, index, params)
    }

    private fun updateLabelState(animate: Boolean) {
        if (!this::textView.isInitialized) {
            return
        }

        val isFocused = android.R.attr.state_focused in drawableState
        val hasText = !text.isNullOrEmpty()

        if ((isFocused && textView is EditText) || hasText) {
            collapseHint(animate)
        } else {
            expandHint(animate)
        }
    }

    private fun collapseHint(animate: Boolean) {
        if (hintAnimator?.isRunning == true) {
            hintAnimator?.cancel()
        }

        if (animate) {
            animateToFraction(0f)
        } else {
            hintAnimationFraction = 0f
        }

        isHintExpanded = false
    }

    private fun expandHint(animate: Boolean) {
        if (hintAnimator?.isRunning == true) {
            hintAnimator?.cancel()
        }

        if (animate) {
            animateToFraction(1f)
        } else {
            hintAnimationFraction = 1f
        }

        isHintExpanded = true
    }

    private fun animateToFraction(target: Float) {
        if (hintAnimationFraction == target) {
            return
        }

        if (hintAnimator == null) {
            hintAnimator = ValueAnimator()
                .apply {
                    interpolator = LinearInterpolator()
                    duration = ANIMATION_DURATION_MS
                    addUpdateListener {
                        hintAnimationFraction = it.animatedValue as Float
                    }
                }
        }
        hintAnimator?.setFloatValues(hintAnimationFraction, target)
        hintAnimator?.start()
    }

    override fun drawableStateChanged() {
        if (isInDrawableStateChange) {
            return
        }

        isInDrawableStateChange = true

        super.drawableStateChanged()

        updateLabelState(true)

        isInDrawableStateChange = false
    }

    override fun onPreDraw(): Boolean {
        hintAnimationFraction = hintAnimationFraction
        viewTreeObserver.removeOnPreDrawListener(this)
        return false
    }
}