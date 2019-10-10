package ru.nobird.android.view.base.ui.adapter.layoutmanager

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WrappingLinearLayoutManager
@JvmOverloads
constructor(
    context: Context,
    orientation: Int = RecyclerView.VERTICAL,
    reverseLayout: Boolean,
    private val columns: Int
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private val dimensions = intArrayOf(0, 0)

    init {
        @Suppress("DEPRECATION")
        isAutoMeasureEnabled = false
    }

    override fun onMeasure(recycler: RecyclerView.Recycler, state: RecyclerView.State, widthSpec: Int, heightSpec: Int) {
        val widthMode = View.MeasureSpec.getMode(widthSpec)
        val heightMode = View.MeasureSpec.getMode(heightSpec)
        val widthSize = View.MeasureSpec.getSize(widthSpec)
        val heightSize = View.MeasureSpec.getSize(heightSpec)

        var width = 0
        var height = 0

        val verticalPadding = paddingTop + paddingBottom
        val horizontalPadding = paddingLeft + paddingEnd

        for (i in 0 until state.itemCount) {
            if (orientation == HORIZONTAL) {
                measureChild(recycler, i, View.MeasureSpec.makeMeasureSpec(widthSize / columns - horizontalPadding, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED), dimensions)

                width += dimensions[0]
                height = maxOf(height, dimensions[1])
            } else {
                measureChild(recycler, i, View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(heightSize / columns - verticalPadding, View.MeasureSpec.EXACTLY), dimensions)

                width = maxOf(width, dimensions[0])
                height += dimensions[1]
            }
        }

        width += horizontalPadding
        height += verticalPadding

        if (widthMode == View.MeasureSpec.EXACTLY) {
            width = widthSize
        }

        if (heightMode == View.MeasureSpec.EXACTLY) {
            height = heightSize
        }

        setMeasuredDimension(width, height)
    }

    override fun addView(child: View) {
        child.layoutParams = child.layoutParams.apply {
            width = RecyclerView.LayoutParams.MATCH_PARENT
            height = RecyclerView.LayoutParams.MATCH_PARENT
        }
        super.addView(child)
    }

    override fun addView(child: View, index: Int) {
        child.layoutParams = child.layoutParams.apply {
            width = RecyclerView.LayoutParams.MATCH_PARENT
            height = RecyclerView.LayoutParams.MATCH_PARENT
        }
        super.addView(child, index)
    }

    override fun measureChildWithMargins(child: View, widthUsed: Int, heightUsed: Int) {
        val lp = child.layoutParams as RecyclerView.LayoutParams

        val widthSpec = getChildMeasureSpec(width / columns, widthMode,
            paddingLeft + paddingRight + lp.leftMargin + lp.rightMargin + widthUsed, lp.width,
            canScrollHorizontally())
        val heightSpec = getChildMeasureSpec(height, heightMode,
            (paddingTop + paddingBottom + lp.topMargin + lp.bottomMargin + heightUsed), lp.height,
            canScrollVertically())

        child.measure(widthSpec, heightSpec)
    }

    private fun measureChild(recycler: RecyclerView.Recycler, position: Int, widthSpec: Int, heightSpec: Int, dimensions: IntArray) {
        val view = recycler.getViewForPosition(position)
        recycler.bindViewToPosition(view, position)

        measure(view, widthSpec, heightSpec, dimensions)
        recycler.recycleView(view)
    }

    private fun measure(view: View, widthSpec: Int, heightSpec: Int, dimensions: IntArray) {
        val params = view.layoutParams as RecyclerView.LayoutParams
        val childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, paddingLeft + paddingRight, params.width)
        val childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, paddingTop + paddingBottom, params.height)
        view.measure(childWidthSpec, childHeightSpec)

        dimensions[0] = view.measuredWidth + params.leftMargin + params.rightMargin
        dimensions[1] = view.measuredHeight + params.bottomMargin + params.topMargin
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams =
        RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT)
}