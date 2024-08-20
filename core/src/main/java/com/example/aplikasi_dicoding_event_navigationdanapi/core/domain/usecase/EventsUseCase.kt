package com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventDetails
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import kotlinx.coroutines.flow.Flow

interface EventsUseCase {
    fun getEvents(): Flow<Resource<List<Events>>>

    fun getFavoriteEvent(): Flow<List<Events>>

    fun getUpcomingEvents(): Flow<List<Events>>
    fun getFinishedEvents(): Flow<List<Events>>
    fun setFavoriteEvent(events: Events, favoriteState: Boolean)
}