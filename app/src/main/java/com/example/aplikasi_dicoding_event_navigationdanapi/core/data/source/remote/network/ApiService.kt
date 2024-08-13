package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.DetailEventResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("events")
    suspend fun getEvent(
    ): EventResponse

    @GET("events/{id}")
    fun getDetailEvent(@Path("id") id: String): Call<DetailEventResponse>
}