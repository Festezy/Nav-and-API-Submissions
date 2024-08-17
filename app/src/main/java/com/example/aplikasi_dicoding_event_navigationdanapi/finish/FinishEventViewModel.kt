package com.example.aplikasi_dicoding_event_navigationdanapi.finish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinishEventViewModel @Inject constructor(private val eventsUseCase: EventsUseCase) :
    ViewModel() {


    private val _listEventItem = MutableLiveData<Resource<List<Events>>>()
    val listEventItem: LiveData<Resource<List<Events>>> = _listEventItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    private val client = ApiConfig.getApiService()

    fun getEvents() =
        eventsUseCase.getEvents().asLiveData()

    companion object {
        private const val TAG = "FinishEventViewModel"
    }
}