package ru.nobird.android.ui.adapters.selection

@Deprecated(message = "Use UDF approach and store selections in a proper place")
object NoChoiceSelectionHelper : SelectionHelper {
    override fun isSelected(position: Int): Boolean =
        false

    override fun toggle(position: Int) {
        // no op
    }

    override fun select(position: Int) {
        // no op
    }

    override fun deselect(position: Int) {
        // no op
    }

    override fun reset() {
        // no op
    }
}