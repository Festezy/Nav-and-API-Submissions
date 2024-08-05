package com.example.aplikasi_dicoding_event_navigationdanapi.core.data

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiService
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors

class EventsRepository private constructor(

){

    companion object{
        @Volatile
        private var instance: EventsRepository? = null
        fun getInstance(
            apiService: ApiService,
//            eventsDao: EventsDao,
            appExecutors: AppExecutors
        ): EventsRepository =
            instance ?: synchronized(this){
                instance ?: EventsRepository(apiService,  appExecutors)
            }.also { instance = it }
    }
}