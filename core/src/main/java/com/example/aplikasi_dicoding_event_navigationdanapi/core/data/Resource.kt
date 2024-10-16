package com.example.aplikasi_dicoding_event_navigationdanapi.core.data

sealed class Resource<out R> private constructor() {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error<out T>(val error: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}