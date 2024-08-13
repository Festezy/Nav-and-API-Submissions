package com.example.aplikasi_dicoding_event_navigationdanapi.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.LocalDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDao
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.RemoteDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.DataMapper

class EventsRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    fun getEvents(active: String): LiveData<Resource<List<EventEntity>>> =
        object : NetworkBoundResource<List<EventEntity>, List<ListEventsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<EventEntity>> {
                return localDataSource.getEvents()
            }

            override fun shouldFetch(data: List<EventEntity>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): LiveData<ApiResponse<List<ListEventsItem>>> =
                remoteDataSource.getEvents(active)

            override suspend fun saveCallResult(data: List<ListEventsItem>) {
                val eventList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertEvent(eventList)
            }
        }.asLiveData()

//    fun getEvents(active: String): LiveData<Resource<List<EventEntity>>> = liveData {
//        emit(Resource.Loading)
//        try {
//            val response = apiService.getEvent(active)
//            val events = response.listEvents
//            val eventsList = events.map { event ->
//                val isFavorite = eventDao.isEventFavorite(event.id.toString())
//                EventEntity(
//                    event.id.toString(),
//                    event.name!!,
//                    event.mediaCover,
//                    isFavorite
//                )
//            }
//            eventDao.deleteAll()
//            eventDao.insertEvent(eventsList)
//        } catch (e: Exception) {
//            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()} ")
//            emit(Resource.Error(e.message.toString(), newData))
//        }
//        val localData: LiveData<Resource<List<EventEntity>>> =
//            eventDao.getEvents().map { Resource.Success(it) }
//        emitSource(localData)
//    }

    fun getFavoriteEvent(): LiveData<List<EventEntity>> {
        return localDataSource.getFavoriteEvent()
    }

    fun setFavoriteEvent(events: EventEntity, favoriteState: Boolean) {
        appExecutors.diskIO.execute { localDataSource.setFavoriteEvent(events, favoriteState) }
    }

    companion object {
        @Volatile
        private var instance: EventsRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): EventsRepository =
            instance ?: synchronized(this) {
                instance ?: EventsRepository(remoteData, localData, appExecutors)
            }.also { instance = it }
    }
}