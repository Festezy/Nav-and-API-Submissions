package com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.repository

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import kotlinx.coroutines.flow.Flow

interface IEventsRepository {
    fun getEvents(): Flow<Resource<List<Events>>>
    fun getFavoriteEvent(): Flow<List<Events>>
    fun getUpcomingEvents(): Flow<List<Events>>
    fun getFinishedEvents(): Flow<List<Events>>
    fun setFavoriteEvent(events: Events, favoriteState: Boolean)
}