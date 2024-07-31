package com.example.aplikasi_dicoding_event_navigationdanapi.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingVIewModel: ViewModel() {
    private val _listEventItem = MutableLiveData<List<ListEventsItem>>()
    val listEventItem: LiveData<List<ListEventsItem>> = _listEventItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val client = ApiConfig.getApiService()
    fun getUpcomingEventList(active: String){
        _isLoading.value = true
        client.getEvent(active).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    _listEventItem.value = responseBody!!.listEvents
                } else {
                    _isLoading.value = true
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object{
        private const val TAG = "MainViewModel"
    }
}