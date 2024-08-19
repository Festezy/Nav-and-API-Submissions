package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao


}