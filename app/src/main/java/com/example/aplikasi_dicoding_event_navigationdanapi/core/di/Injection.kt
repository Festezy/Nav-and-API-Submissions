package com.example.aplikasi_dicoding_event_navigationdanapi.core.di

import android.content.Context
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.LocalDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDatabase
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.RemoteDataSource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.repository.IEventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsInteractor
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IEventsRepository {
        val apiService = ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource.getInstance(apiService)

        val database = EventDatabase.getInstance(context)
        val dao = database.eventDao()
        val localDataSource = LocalDataSource.getInstance(dao)

        val appExecutors = AppExecutors()
        return EventsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideEventsUseCase(context: Context): EventsUseCase {
        val repository = provideRepository(context)
        return EventsInteractor(repository)
    }
}