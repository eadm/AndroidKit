package ru.nobird.android.ui.adapters.selection

import androidx.recyclerview.widget.RecyclerView

@Deprecated(message = "Use UDF approach and store selections in a proper place")
class MultipleChoiceSelectionHelper(
    private val adapter: RecyclerView.Adapter<*>
) : SelectionHelper {
    private val selectedPositions = mutableSetOf<Int>()

    override fun isSelected(position: Int): Boolean =
        position in selectedPositions

    override fun toggle(position: Int) {
        if (position in selectedPositions) {
            deselect(position)
        } else {
            select(position)
        }
    }

    override fun select(position: Int) {
        selectedPositions += position
        adapter.notifyItemChanged(position)
    }

    override fun deselect(position: Int) {
        selectedPositions -= position
        adapter.notifyItemChanged(position)
    }

    override fun reset() {
        selectedPositions.clear()
    }
}