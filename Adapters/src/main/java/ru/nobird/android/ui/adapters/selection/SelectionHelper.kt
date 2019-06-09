package ru.nobird.android.ui.adapters.selection

/**
 * Interface to handle selection with recycler view adapters
 */
interface SelectionHelper {
    /**
     * Returns true if item at [position] is selected
     */
    fun isSelected(position: Int): Boolean

    /**
     * If item at [position] is selected it will become deselected, selected otherwise
     */
    fun toggle(position: Int)

    /**
     * Selects item at [position]
     */
    fun select(position: Int)

    /**
     * Deselects item at [position]
     */
    fun deselect(position: Int)

    /**
     * Resets selection
     */
    fun reset()
}