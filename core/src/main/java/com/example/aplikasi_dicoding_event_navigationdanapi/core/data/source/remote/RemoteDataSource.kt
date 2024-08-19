package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote

import android.util.Log
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventDetails
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getEvents(): Flow<ApiResponse<List<ListEventsItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getEvent()
                val dataArray = response.listEvents
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.listEvents))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailEvent(id: String): Flow<ApiResponse<EventDetails>> {
        //get data from remote api
        return flow {
            emit(ApiResponse.Empty)
            try {
                val response = apiService.getDetailEvent(id)
                val dataArray = response.event
                if (dataArray != null) {
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

