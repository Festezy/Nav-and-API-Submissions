package com.example.aplikasi_dicoding_event_navigationdanapi.core.data

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.LocalDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.RemoteDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventDetails
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.repository.IEventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IEventsRepository {

    override fun getEvents(): Flow<Resource<List<Events>>> =
        object : NetworkBoundResource<List<Events>, List<ListEventsItem>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Events>> {
                return localDataSource.getEvents().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Events>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ListEventsItem>>> =
                remoteDataSource.getEvents()

            override suspend fun saveCallResult(data: List<ListEventsItem>) {
                val eventList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertEvent(eventList)
            }
        }.asFlow()

    override fun getUpcomingEvents(): Flow<List<Events>> {
        return localDataSource.getUpcomingEvents().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFinishedEvents(): Flow<List<Events>> {
        return localDataSource.getFinishedEvents().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getDetailEvent(id: String): Flow<ApiResponse<EventDetails>> {
        return flow {
            try {
                emitAll(remoteDataSource.getDetailEvent(id))
            } catch (e: Exception) {
                // Handle the exception, e.g., emit an error state
                emit(ApiResponse.Error(e.message ?: "Unknown error"))
            }
        }
    }


    override fun getFavoriteEvent(): Flow<List<Events>> {
        return localDataSource.getFavoriteEvent().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteEvent(events: Events, favoriteState: Boolean) {
        val eventsEntity = DataMapper.mapDomainToEntity(events)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteEvent(eventsEntity, favoriteState) }
    }


}