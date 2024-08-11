package com.example.aplikasi_dicoding_event_navigationdanapi.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.detail.DetailsViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.core.di.Injection
import com.example.aplikasi_dicoding_event_navigationdanapi.favorite.FavoriteViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.finish.FinishEventViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.upcoming.UpcomingVIewModel

class ViewModelFactory private constructor(
    private val eventsRepository: EventsRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpcomingVIewModel::class.java)) {
            return UpcomingVIewModel(eventsRepository) as T
        } else if (modelClass.isAssignableFrom(FinishEventViewModel::class.java)) {
            return FinishEventViewModel(eventsRepository) as T
        } else if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(eventsRepository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(eventsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}