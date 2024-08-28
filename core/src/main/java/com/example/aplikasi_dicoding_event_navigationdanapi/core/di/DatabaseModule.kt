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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): EventDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java, "event.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideTourismDao(database: EventDatabase): EventDao = database.eventDao()
}