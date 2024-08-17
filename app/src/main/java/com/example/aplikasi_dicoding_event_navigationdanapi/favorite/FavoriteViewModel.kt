package com.example.aplikasi_dicoding_event_navigationdanapi.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val eventsUseCase: EventsUseCase) :
    ViewModel() {
    private val _listFavoriteEvents = MutableLiveData<List<Events>>()
    val listFavoriteEvents: LiveData<List<Events>> = _listFavoriteEvents

    private val favoriteEvents = eventsUseCase.getFavoriteEvent()

    fun getFavoriteEvents() =
        viewModelScope.launch {
            favoriteEvents.collectLatest {
                _listFavoriteEvents.value = it
            }
        }
}