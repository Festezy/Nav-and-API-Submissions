package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val eventDao: EventDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(eventDao: EventDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(eventDao)
            }
    }

    fun getEvents(): Flow<List<EventEntity>> = eventDao.getEvents()

    fun getFavoriteEvent(): Flow<List<EventEntity>> = eventDao.getFavoriteEvent()

    suspend fun insertEvent(eventList: List<EventEntity>) = eventDao.insertEvent(eventList)

    fun setFavoriteEvent(event: EventEntity, newState: Boolean) {
        event.isFavorite = newState
        eventDao.updateEvent(event)
    }
}