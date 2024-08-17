package com.example.aplikasi_dicoding_event_navigationdanapi.core.di

import android.content.Context
import androidx.room.Room
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDao
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room.EventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): EventDatabase = Room.databaseBuilder(
        context,
        EventDatabase::class.java, "event.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: EventDatabase): EventDao = database.eventDao()
}