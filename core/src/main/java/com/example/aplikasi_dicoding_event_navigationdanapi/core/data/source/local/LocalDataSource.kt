package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val eventDao: EventDao) {


    fun getEvents(): Flow<List<EventEntity>> = eventDao.getEvents()

    fun getFavoriteEvent(): Flow<List<EventEntity>> = eventDao.getFavoriteEvent()

    suspend fun insertEvent(eventList: List<EventEntity>) = eventDao.insertEvent(eventList)

    fun setFavoriteEvent(event: EventEntity, newState: Boolean) {
        event.isFavorite = newState
        eventDao.updateEvent(event)
    }
}