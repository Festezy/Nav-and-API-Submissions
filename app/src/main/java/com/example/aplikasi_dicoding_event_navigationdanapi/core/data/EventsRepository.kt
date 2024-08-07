package com.example.aplikasi_dicoding_event_navigationdanapi.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDao
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors

class EventsRepository private constructor(
    private val apiService: ApiService,
    private val eventDao: EventDao,
    private val appExecutors: AppExecutors
){

    fun getEvents(active: String): LiveData<ApiResult<List<EventEntity>>> = liveData {
        emit(ApiResult.Loading)
        try {
            val response = apiService.getEvent(active)
            val events = response.listEvents
            val eventsList = events.map { event ->
                val isFavorite = eventDao.isEventFavorite(event.id.toString())
                EventEntity(
                    event.id.toString(),
                    event.name!!,
                    event.mediaCover,
                    isFavorite
                )
            }
            eventDao.deleteAll()
            eventDao.insertEvent(eventsList)
        } catch (e: Exception){
            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
            emit(ApiResult.Error(e.message.toString()))
        }
        val localData: LiveData<ApiResult<List<EventEntity>>> = eventDao.getEvents().map { ApiResult.Success(it) }
        emitSource(localData)
    }

    fun getFavoriteEvent(): LiveData<List<EventEntity>> {
        return eventDao.getFavoriteEvent()
    }
    suspend fun setFavoriteEvent(events: EventEntity, favoriteState: Boolean) {
        events.isFavorite = favoriteState
            eventDao.updateEvent(events)
    }

    companion object{
        @Volatile
        private var instance: EventsRepository? = null
        fun getInstance(
            apiService: ApiService,
            eventsDao: EventDao,
            appExecutors: AppExecutors
        ): EventsRepository =
            instance ?: synchronized(this){
                instance ?: EventsRepository(apiService,  eventsDao, appExecutors)
            }.also { instance = it }
    }
}