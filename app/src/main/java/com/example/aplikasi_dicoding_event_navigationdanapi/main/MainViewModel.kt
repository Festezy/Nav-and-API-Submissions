package com.example.aplikasi_dicoding_event_navigationdanapi.main

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

class MainViewModel: ViewModel() {

    private val _listEventItem = MutableLiveData<List<ListEventsItem>>()
    val listEventItem: LiveData<List<ListEventsItem>> = _listEventItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val client = ApiConfig.getApiService()

//    private val _value = MutableLiveData<String>()
//    val value = _value
//
//    init {
//        getEventList()
//    }



    fun getEventList(active: String){
        client.getEvent(active).enqueue(object : Callback<EventResponse>{
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    _listEventItem.value = responseBody!!.listEvents
                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object{
        private const val TAG = "MainViewModel"
    }
}