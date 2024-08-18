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
    private val _listEventDetail = MutableLiveData<ApiResponse<EventDetails>>()
    val listEventDetail: LiveData<ApiResponse<EventDetails>> = _listEventDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun saveFavorite(event: Events, newStatus: Boolean) {
        viewModelScope.launch {
            eventsUseCase.setFavoriteEvent(event, newStatus)
        }
    }

    fun getEventDetail(id: String) {
        viewModelScope.launch {
            val getData = eventsUseCase.getDetailEvent(id)
            getData.collectLatest { apiResponse ->
                _listEventDetail.value = apiResponse
            }
        }
    }

    companion object {
        private const val TAG = "DetailsViewModel"
    }
}