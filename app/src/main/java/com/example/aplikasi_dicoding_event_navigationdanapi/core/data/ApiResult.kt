package com.example.aplikasi_dicoding_event_navigationdanapi.core.data

sealed class ApiResult<out R> private constructor() {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val error: String) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}