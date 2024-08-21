package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.DetailEventResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("events")
    suspend fun getEvent(
    ): EventResponse

}