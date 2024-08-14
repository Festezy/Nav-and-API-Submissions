package com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Events(
    var id: String = "",

    var name: String = "",

    var mediaCover: String? = null,

    var isFavorite: Boolean
) : Parcelable
