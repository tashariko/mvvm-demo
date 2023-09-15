package com.tasha.data

data class ApiResult<out T>(val status: Status, val data: T?, val errorMessage: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): ApiResult<T> {
            return ApiResult(Status.SUCCESS, data, null)
        }

        fun <T> error(errorMessage: String? = null, data: T? = null): ApiResult<T> {
            return ApiResult(Status.ERROR, data, errorMessage)
        }

        fun <T> loading(data: T? = null): ApiResult<T> {
            return ApiResult(Status.LOADING, data, null)
        }
    }
}