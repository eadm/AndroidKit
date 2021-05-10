package ru.nobird.android.core.model

/**
 * Flattens collection of long arrays
 */
actual fun Iterable<LongArray>.flatten(): LongArray {
    val size = sumBy { it.size }
    val array = LongArray(size)
    var offset = 0
    forEach { subArray ->
        subArray.copyInto(array, destinationOffset = offset)
        offset += subArray.size
    }

    return array
}

/**
 * Removes duplicates from long array
 */
actual fun LongArray.distinct(): LongArray =
    toMutableSet().toLongArray()