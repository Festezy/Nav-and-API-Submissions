package com.example.aplikasi_dicoding_event_navigationdanapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteViewModel(private val eventsRepository: EventsRepository) : ViewModel() {
    private val _listFavoriteEvents = MutableLiveData<List<EventEntity>>()
    val listFavoriteEvents: LiveData<List<EventEntity>> = _listFavoriteEvents

    private val favoriteEvents = eventsRepository.getFavoriteEvent().asFlow()

    fun getFavoriteEvents() =
        viewModelScope.launch {
            favoriteEvents.collectLatest {
                _listFavoriteEvents.value = it
            }
        }
}