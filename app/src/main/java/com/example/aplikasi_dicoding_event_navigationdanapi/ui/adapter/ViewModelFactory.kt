package com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.SettingPreferences
import com.example.aplikasi_dicoding_event_navigationdanapi.detail.DetailsViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.di.Injection
import com.example.aplikasi_dicoding_event_navigationdanapi.favorite.FavoriteViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.finish.FinishEventViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.main.MainViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.setting.SettingsViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.upcoming.UpcomingVIewModel

class ViewModelFactory private constructor(
    private val eventsRepository: EventsRepository,
    private val pref: SettingPreferences
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
        } else if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(pref) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context), Injection.providePreferences(context))
            }.also { instance = it }
    }
}