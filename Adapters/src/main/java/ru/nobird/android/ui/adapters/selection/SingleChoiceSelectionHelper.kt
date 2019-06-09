package ru.nobird.android.ui.adapters.selection

import androidx.recyclerview.widget.RecyclerView

class SingleChoiceSelectionHelper(
    private val adapter: RecyclerView.Adapter<*>
) : SelectionHelper {
    private var selectedPosition = -1

    override fun isSelected(position: Int): Boolean =
        position == selectedPosition

    override fun toggle(position: Int) {
        if (selectedPosition == position) {
            deselect(position)
        } else {
            select(position)
        }
    }

    override fun select(position: Int) {
        val oldPosition = selectedPosition
        selectedPosition = position
        adapter.notifyItemChanged(oldPosition)
        adapter.notifyItemChanged(position)
    }

    override fun deselect(position: Int) {
        if (selectedPosition == position) {
            selectedPosition = -1
            adapter.notifyItemChanged(position)
        }
    }

    override fun reset() {
        selectedPosition = -1
    }
}