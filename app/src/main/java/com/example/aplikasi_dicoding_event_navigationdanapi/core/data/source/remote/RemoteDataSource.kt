package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote

import android.util.Log
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

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

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }
}

