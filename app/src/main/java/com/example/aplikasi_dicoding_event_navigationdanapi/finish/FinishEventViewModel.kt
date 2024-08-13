package com.example.aplikasi_dicoding_event_navigationdanapi.finish

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.EventsRepository
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishEventViewModel(private val eventsRepository: EventsRepository): ViewModel() {


    private val _listEventItem = MutableLiveData<List<ListEventsItem>>()
    val listEventItem: LiveData<List<ListEventsItem>> = _listEventItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val client = ApiConfig.getApiService()

    fun getEvents() = eventsRepository.getEvents()

    companion object{
        private const val TAG = "FinishEventViewModel"
    }
}