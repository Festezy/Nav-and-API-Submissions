package com.example.aplikasi_dicoding_event_navigationdanapi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventDetails
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val eventsUseCase: EventsUseCase) : ViewModel() {

    fun saveFavorite(event: Events, newStatus: Boolean) {
        viewModelScope.launch {
            eventsUseCase.setFavoriteEvent(event, newStatus)
        }
    }


    companion object {
        private const val TAG = "DetailsViewModel"
    }
}