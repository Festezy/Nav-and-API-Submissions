package com.example.aplikasi_dicoding_event_navigationdanapi.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiConfig
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.DetailEventResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.EventDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {
    private val _listEventDetail = MutableLiveData<EventDetails>()
    val listEventDetail: LiveData<EventDetails> = _listEventDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val client = ApiConfig.getApiService()

    fun getDetailData(id: String){
        _isLoading.value = true
        client.getDetailEvent(id).enqueue(object : Callback<DetailEventResponse>{
            override fun onResponse(
                call: Call<DetailEventResponse>,
                response: Response<DetailEventResponse>
            ) {
                if (response.isSuccessful){
                    _isLoading.value = false
                    _listEventDetail.value = response.body()!!.event
                } else {
                    _isLoading.value = true
                }
            }

            override fun onFailure(call: Call<DetailEventResponse>, t: Throwable) {
                _isLoading.value = true
            }

        })
    }

    companion object{
        private const val TAG = "DetailsViewModel"
    }
}