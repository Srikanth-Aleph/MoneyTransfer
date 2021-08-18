package com.moneytransfer.core

/**
 * Encapsulates the outcome of an operation with either [Success.data] of T or [Error.exception]. Response
 * is typically used for wrapping the output of repository functions (E.g. network calls).
 *
 * Not to be confused with [Result] which is intended for use cases.
 *
 * @param T
 */
sealed class Response<out T> {

    /**
     * Encapsulates the successful completion of an operation.
     *
     * @param T
     * @property data The result of the operation.
     */
    data class Success<out T>(val data: T) : Response<T>()

    /**
     * Encapsulates the failure of an operation.
     *
     * @property exception The exception produced by the operation
     */
    data class Error(val exception: Exception) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val Response<*>.succeeded
    get() = this is Response.Success && data != null
