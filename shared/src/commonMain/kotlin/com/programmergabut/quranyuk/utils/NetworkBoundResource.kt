package com.programmergabut.quranyuk.utils

suspend inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> ResultType,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
): ResultType {
    val data = query()
    val flow = if (shouldFetch(data)) {
        saveFetchResult(fetch())
        query()
    } else {
        query()
    }
    return flow
}