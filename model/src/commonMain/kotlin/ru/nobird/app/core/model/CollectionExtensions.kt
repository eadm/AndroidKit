package ru.nobird.app.core.model

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
expect fun Iterable<LongArray>.flatten(): LongArray

/**
 * Removes duplicates from long array
 */
expect fun LongArray.distinct(): LongArray

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

/**
 * Creates new list from current with inserted [item] at [pos]
 */
fun <T> List<T>.insert(item: T, pos: Int = size): List<T> {
    val list = mutableListOf<T>()
    list.addAll(this.slice(to = pos))
    list.add(item)
    list.addAll(this.slice(from = pos))
    return list
}

/**
 * Creates sublist as view from current
 */
fun <T> List<T>.slice(from: Int = 0, to: Int = size): List<T> =
    subList(from.coerceAtLeast(0), to.coerceAtMost(size))

/**
 * Puts [value] in map if it is not null
 */
fun <K, V> MutableMap<K, V>.putNullable(key: K, value: V?) {
    if (value != null) {
        put(key, value)
    }
}

/**
 * Creates map from [pairs] where value is not null
 */
fun <K, V : Any> mapOfNotNull(vararg pairs: Pair<K, V?>): Map<K, V> =
    pairs
        .mapNotNull { (first, second) ->
            second?.let { first to it }
        }
        .toMap()

/**
 * Returns true if first elements of current list equals to ones from [other]
 */
fun <T> List<T>.startsWith(other: List<T>): Boolean {
    if (size < other.size) return false

    for (i in 0 until minOf(size, other.size)) {
        if (this[i] != other[i]) return false
    }
    return true
}

/**
 * Casts this to [T]
 */
inline fun <reified T> Any?.cast(): T =
    this as T

/**
 * Casts this to [T] or returns null if cast cannot be performed
 */
inline fun <reified T> Any?.safeCast(): T? =
    this as? T