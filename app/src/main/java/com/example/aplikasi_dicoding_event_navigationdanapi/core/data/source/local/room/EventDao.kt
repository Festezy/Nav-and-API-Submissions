package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM event ORDER BY id ASC")
    fun getEvents(): Flow<List<EventEntity>>

    @Query("SELECT * FROM event where isFavorite = 1")
    fun getFavoriteEvent(): Flow<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(event: List<EventEntity>)

    @Update
    fun updateEvent(event: EventEntity)

    @Query("DELETE FROM event WHERE isFavorite = 0")
    fun deleteAll()

    @Query("SELECT EXISTS(SELECT * FROM event WHERE id = :id AND isFavorite = 1)")
    fun isEventFavorite(id: String): Boolean
}