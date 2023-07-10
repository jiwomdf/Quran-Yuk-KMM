package com.programmergabut.quranyuk.utils

sealed class ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error<Nothing>(val exception: Exception): ApiResponse<Nothing>()
    data class Loading<T>(val data: T?): ApiResponse<T>()

    fun isSuccess(): Boolean = this is Success

    fun get(): T = (this as Success).data
}