package io.timemates.sdk.common.pagination

import io.timemates.sdk.common.annotations.ApiStatus
import io.timemates.sdk.common.annotations.ExperimentalApi
import io.timemates.sdk.common.constructor.createOrThrow
import io.timemates.sdk.common.exceptions.TimeMatesException
import io.timemates.sdk.common.pagination.PagesIteratorImpl.State
import io.timemates.sdk.common.types.TimeMatesEntity
import io.timemates.sdk.common.types.value.Count
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlin.time.Duration

/**
 * Interface representing a page iterator for paginated data retrieval.
 *
 * @param T The type of elements in the page.
 */
public interface PagesIterator<T> {
    /**
     * Returns `true` if there is another page available, `false` otherwise.
     *
     * @return `true` if there is another page, `false` otherwise.
     */
    public suspend operator fun hasNext(): Boolean

    /**
     * Returns the next page of elements.
     *
     * @return The list of elements in the next page.
     * @throws NoSuchElementException if there are no more pages available.
     */
    @Throws(NoSuchElementException::class)
    public suspend operator fun next(): List<T>

    /**
     * Returns an iterator over the pages.
     *
     * @return The iterator itself.
     */
    public operator fun iterator(): PagesIterator<T> = this
}

/**
 * Converts the [PagesIterator] into a [Flow] of lists of elements.
 *
 * @return A [Flow] that emits lists of elements from the page iterator.
 */
public fun <T> PagesIterator<T>.asFlow(): Flow<List<T>> = flow {
    for (elements in this@asFlow)
        emit(elements)
}

/**
 * Collects all elements from the [PagesIterator] into a [List].
 *
 * @return A [List] containing all elements from the page iterator.
 */
public suspend fun <T> PagesIterator<T>.toList(): List<T> {
    return buildList {
        for (elements in this@toList)
            addAll(elements)
    }
}

/**
 * Converts the elements from the [PagesIterator] into a [Sequence].
 *
 * **Note that it's actually iterated at first time.** This is the reason, why
 * it's actually experimental.
 *
 * @return A [Sequence] containing all elements from the page iterator.
 */
@ExperimentalApi(status = ApiStatus.NEEDS_REVISION)
public suspend fun <T> PagesIterator<T>.asSequence(): Sequence<T> {
    return toList().asSequence()
}

/**
 * Performs the given [block] on each page of elements from the [PagesIterator].
 *
 * @param block The block to be executed on each page of elements.
 */
public suspend inline fun <T> PagesIterator<T>.forEachPage(block: (List<T>) -> Unit) {
    while (hasNext()) {
        block(next())
    }
}

/**
 * Performs the given [block] on each element from the [PagesIterator].
 *
 * @param block The block to be executed on each element.
 */
public suspend inline fun <T> PagesIterator<T>.forEach(block: (T) -> Unit) {
    forEachPage { page ->
        page.forEach(block)
    }
}


/**
 * Internal implementation class for the [PagesIterator] interface.
 *
 * @param T The type of elements in the page.
 * @property chunkSize The number of elements per page.
 * @property initialPageToken The initial page token to start from.
 * @property provider The function responsible for fetching pages of data.
 * @property delayOnServerErrors Delay between retries
 * @property maxRetries Max count of retries until iterator reaches [State.DONE] state.
 * @property increaseDelayOnRetries Determines whether we should increase initial [delayOnServerErrors]
 * on every retry.
 */
internal class PagesIteratorImpl<T : TimeMatesEntity>(
    private val chunkSize: Count = Count.createOrThrow(20),
    private val initialPageToken: PageToken?,
    prevPage: Page<T>? = null,
    private val provider: suspend (size: Count, pageToken: PageToken?) -> Result<Page<T>>,
    private val delayOnServerErrors: Duration,
    private val maxRetries: Count = Count.createOrThrow(5),
    private val increaseDelayOnRetries: Boolean = true,
) : PagesIterator<T> {
    /**
     * Enum representing the state of the page iterator.
     *
     * - **DONE** – no more elements present.
     * - **UNKNOWN** – initial state of the iterator, needs to be queried first.
     * - **READY** – has more elements to consume (it's sdk-side decision, so there's possibility that
     * next page has no elements at all).
     */
    private enum class State {
        UNKNOWN, READY, DONE,
    }

    private var state: State = State.UNKNOWN
    private var lastPage: Page<T>? = prevPage

    /**
     * Returns `true` if there is another page available, `false` otherwise.
     */
    override suspend fun hasNext(): Boolean {
        return state != State.DONE
    }

    /**
     * Returns the next page of elements.
     *
     * @throws NoSuchElementException if there are no more pages available.
     */
    override suspend fun next(): List<T> {
        if (state == State.DONE)
            throw NoSuchElementException("No more pages.")

        var delayOnServerErrors = delayOnServerErrors.inWholeMilliseconds

        return createPageFlow()
            .catch {
                delay(delayOnServerErrors)
                if (increaseDelayOnRetries)
                    delayOnServerErrors *= 2
            }
            .retry(maxRetries.int.toLong()) { cause -> cause is TimeMatesException }
            .firstOrNull()
            ?: emptyList<T>().also { state = State.DONE }
    }

    private fun createPageFlow(): Flow<List<T>> = flow {
        val nextPageToken = lastPage?.nextPageToken ?: initialPageToken

        val result = provider(chunkSize, nextPageToken)
        val page = result.getOrThrow()

        state = if (page.results.size < chunkSize.int || page.nextPageToken == null) {
            State.DONE
        } else {
            State.READY
        }

        lastPage = page

        emit(page.results)
    }
}