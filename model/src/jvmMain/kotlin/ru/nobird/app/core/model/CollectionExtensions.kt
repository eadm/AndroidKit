@file:JvmName("CollectionExtensions")
@file:JvmMultifileClass
package ru.nobird.app.core.model

/**
 * Flattens collection of long arrays
 */
@JvmName("Iterable_LongArray__flatten")
actual fun Iterable<LongArray>.flatten(): LongArray {
    val size = sumOf { it.size }
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
@JvmName("LongArray__distinct")
actual fun LongArray.distinct(): LongArray =
    toMutableSet().toLongArray()