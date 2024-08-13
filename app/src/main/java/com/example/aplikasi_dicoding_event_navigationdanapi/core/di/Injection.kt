package com.example.aplikasi_dicoding_event_navigationdanapi.core.di

import android.content.Context
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.LocalDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDatabase
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.RemoteDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): EventsRepository {
        val apiService = ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource.getInstance(apiService)

        val database = EventDatabase.getInstance(context)
        val dao = database.eventDao()
        val localDataSource = LocalDataSource.getInstance(dao)

        val appExecutors = AppExecutors()
        return EventsRepository.getInstance(remoteDataSource,  localDataSource, appExecutors)
    }
}