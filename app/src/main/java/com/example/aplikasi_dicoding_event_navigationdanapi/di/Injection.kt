package com.example.aplikasi_dicoding_event_navigationdanapi.di

import android.content.Context
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): EventsRepository {
        val apiService = ApiConfig.getApiService()
//        val database = EventsDatabase.getInstance(context)
//        val dao = database.eventsDao()
        val appExecutors = AppExecutors()
        return EventsRepository.getInstance(apiService,  appExecutors)
    }
}