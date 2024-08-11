package com.example.aplikasi_dicoding_event_navigationdanapi.di

import android.content.Context
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDatabase
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.AppExecutors
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.SettingPreferences
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.dataStore

object Injection {
    fun provideRepository(context: Context): EventsRepository {
        val apiService = ApiConfig.getApiService()
        val database = EventDatabase.getInstance(context)
        val dao = database.eventDao()
        val appExecutors = AppExecutors()
        return EventsRepository.getInstance(apiService,  dao, appExecutors)
    }

    fun providePreferences(context: Context): SettingPreferences{
        val pref = context.dataStore
        return SettingPreferences.getInstance(pref)
    }
}