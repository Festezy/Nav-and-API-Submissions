package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem

class RemoteDataSource private constructor(private val apiService: ApiService) {

    suspend fun getEvents(active: String): LiveData<ApiResponse<List<ListEventsItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<ListEventsItem>>>()
        try {
            val response = apiService.getEvent(active)
            val events = response.listEvents
            resultData.value =
                if (events != null) ApiResponse.Success(events) else ApiResponse.Empty
        } catch (e: Exception) {
            resultData.value = ApiResponse.Error(e.message.toString())
            Log.e("RemoteDataSource", e.message.toString())
        }

        return resultData
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

