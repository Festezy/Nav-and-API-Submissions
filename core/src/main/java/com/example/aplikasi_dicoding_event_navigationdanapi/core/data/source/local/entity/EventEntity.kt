package com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "event")
data class EventEntity(
    @field:ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: String = "",

    @field:ColumnInfo(name = "name")
    var name: String? = "",

    @field:ColumnInfo(name = "mediaCover")
    var mediaCover: String? = null,

    @field:ColumnInfo(name = "registrants")
    val registrants: Int? = null,

    @field:ColumnInfo(name = "summary")
    val summary: String? = null,

    @field:ColumnInfo(name = "imageLogo")
    val imageLogo: String? = null,

    @field:ColumnInfo(name = "link")
    val link: String? = null,

    @field:ColumnInfo(name = "description")
    val description: String? = null,

    @field:ColumnInfo(name = "ownerName")
    val ownerName: String? = null,

    @field:ColumnInfo(name = "cityName")
    val cityName: String? = null,

    @field:ColumnInfo(name = "quota")
    val quota: Int? = null,

    @field:ColumnInfo(name = "beginTime")
    val beginTime: String? = null,

    @field:ColumnInfo(name = "endTime")
    val endTime: String? = null,

    @field:ColumnInfo(name = "category")
    val category: String? = null,

    @field:ColumnInfo(name = "isExpired")
    var isExpired: Boolean,

    @field:ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
): Parcelable