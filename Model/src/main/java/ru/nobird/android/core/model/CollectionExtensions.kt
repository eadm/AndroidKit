package ru.nobird.android.core.model

fun <T: Comparable<T>> Array<T>.isOrdered(): Boolean =
    (0 until this.size - 1).none { this[it] > this[it + 1] }

fun <T: Comparable<T>> Array<T>.isNotOrdered(): Boolean =
    !this.isOrdered()

fun <T: Comparable<T>> Array<T>.isOrderedDesc(): Boolean =
    (0 until this.size - 1).none { this[it] < this[it + 1] }

/**
 * Maps collection straight to long array
 */
inline fun <T> Collection<T>.mapToLongArray(transform: (T) -> Long): LongArray {
    val array = LongArray(this.size)
    forEachIndexed { index, t ->
        array[index] = transform(t)
    }
    return array
}

/**
 * Maps array straight to long array
 */
inline fun <T> Array<T>.mapToLongArray(transform: (T) -> Long): LongArray {
    val array = LongArray(this.size)
    forEachIndexed { index, t ->
        array[index] = transform(t)
    }
    return array
}

fun LongArray?.isNullOrEmpty(): Boolean =
    this == null || this.isEmpty()

/**
 * Flattens collection of long arrays
 */
@JvmName("Iterable_LongArray__flatten")
fun Iterable<LongArray>.flatten(): LongArray {
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
@JvmName("LongArray_distinct")
fun LongArray.distinct(): LongArray =
    toMutableSet().toLongArray()

/**
 * Immutable swap
 */
fun <T> List<T>.swap(i: Int, j: Int): List<T> {
    if (i !in 0 until size ||
        j !in 0 until size) {
        return this
    }

    val a = this[i]
    val b = this[j]
    return mapIndexed { index, t ->
        when (index) {
            i -> b
            j -> a
            else -> t
        }
    }
}

/**
 * Applies mutation to list
 */
inline fun <T> List<T>.mutate(mutation: MutableList<T>.() -> Unit): List<T> =
    this.toMutableList().apply(mutation)

/**
 * Applies mutation to list
 */
inline fun <T> PagedList<T>.mutate(mutation: MutableList<T>.() -> Unit): PagedList<T> =
    PagedList(this.toMutableList().apply(mutation), hasPrev = hasPrev, hasNext = hasNext, page = page)