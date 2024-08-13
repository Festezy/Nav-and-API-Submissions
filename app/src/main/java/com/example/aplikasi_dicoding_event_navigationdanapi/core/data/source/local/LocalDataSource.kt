package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDao

class LocalDataSource private constructor(private val eventDao: EventDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(eventDao: EventDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(eventDao)
            }
    }

    fun getEvents(): LiveData<List<EventEntity>> = eventDao.getEvents()

    fun getFavoriteEvent(): LiveData<List<EventEntity>> = eventDao.getFavoriteEvent()

    suspend fun insertEvent(eventList: List<EventEntity>) = eventDao.insertEvent(eventList)

    fun setFavoriteEvent(event: EventEntity, newState: Boolean) {
        event.isFavorite = newState
        eventDao.updateEvent(event)
    }
}