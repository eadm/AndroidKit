package ru.nobird.android.domain.rx

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

inline fun <T> Maybe<T>.doCompletableOnSuccess(crossinline completableSource: (T) -> Completable): Maybe<T> =
    flatMap { completableSource(it).andThen(Maybe.just(it)) }

inline fun <T> Single<T>.doCompletableOnSuccess(crossinline completableSource: (T) -> Completable): Single<T> =
    flatMap { completableSource(it).andThen(Single.just(it)) }

/**
 * Check list for size or return error
 */
fun <T> Single<List<T>>.requireSize(size: Int): Single<List<T>> =
    flatMap { list ->
        list.takeIf { it.size == size }
            ?.let { Single.just(it) }
            ?: Single.error(IllegalStateException("Expected list size = $size, actual = ${list.size}"))
    }

/**
 * Empty on error stub in order to suppress errors
 */
val emptyOnErrorStub: (Throwable) -> Unit = {}

/**
 * Filters observable according to [predicateSource] predicate
 */
fun <T> Observable<T>.filterSingle(predicateSource: (T) -> Single<Boolean>): Observable<T> =
    flatMap { item ->
        predicateSource(item)
            .toObservable()
            .filter { it }
            .map { item }
    }

/**
 * Wraps current object to Maybe
 */
fun <T : Any> T?.toMaybe(): Maybe<T> =
    if (this == null) {
        Maybe.empty()
    } else {
        Maybe.just(this)
    }

/**
 * Returns first element of iterable wrapped in Maybe or empty
 */
fun <T : Any> Single<out Iterable<T>>.maybeFirst(): Maybe<T> =
    flatMapMaybe { it.firstOrNull().toMaybe() }

/**
 * Returns first element of iterable
 */
fun <T : Any> Single<out Iterable<T>>.first(): Single<T> =
    map { it.first() }