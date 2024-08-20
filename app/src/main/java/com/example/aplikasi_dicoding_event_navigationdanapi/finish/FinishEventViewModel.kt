package com.example.aplikasi_dicoding_event_navigationdanapi.finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinishEventViewModel @Inject constructor(private val eventsUseCase: EventsUseCase) :
    ViewModel() {

    private val _listEventItem = MutableLiveData<List<Events>>()
    val listEventItem: LiveData<List<Events>> = _listEventItem

    init {
        getFinishEvents()
    }

    fun getEvents() =
        eventsUseCase.getEvents().asLiveData()

    private fun getFinishEvents() {
        viewModelScope.launch {
            eventsUseCase.getFinishedEvents().collectLatest {
                _listEventItem.value = it
            }
        }
    }

}