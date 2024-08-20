package com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Events(
    var id: String = "",
    val name: String? = null,
    val mediaCover: String? = null,
    val registrants: Int? = null,
    val summary: String? = null,
    val imageLogo: String? = null,
    val link: String? = null,
    val description: String? = null,
    val ownerName: String? = null,
    val cityName: String? = null,
    val quota: Int? = null,
    val beginTime: String? = null,
    val endTime: String? = null,
    val category: String? = null,
    var isExpired: Boolean,
    var isFavorite: Boolean
) : Parcelable
