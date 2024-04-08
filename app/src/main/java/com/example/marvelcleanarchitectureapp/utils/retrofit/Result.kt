package com.example.myapplication.core.util

class Result<R, F : Exception> private constructor() {
    var failure: F? = null
        private set

    var isFailure: Boolean = false
        private set

    var isSuccess: Boolean = false
        private set

    var result: R? = null
        private set

    private constructor(failure: F) : this() {
        this.failure = failure
        this.isFailure = true
    }

    private constructor(result: R) : this() {
        this.isSuccess = true
        this.result = result
    }

    inline fun onSuccess(action: (value: R) -> Unit): Result<R, F> {
        if (isSuccess) action(result!!)
        return  this
    }

    inline fun onFailure(action: (exception: F) -> Unit): Result<R, F> {
        if (isFailure) action(failure!!)
        return this
    }

    companion object {
        @JvmStatic
        fun <R, F : Exception> failure(failure: F): Result<R, F> = Result<R, F>(failure)

        @JvmStatic
        fun <R, F : Exception> success(result: R): Result<R, F> = Result<R, F>(result)
    }
}