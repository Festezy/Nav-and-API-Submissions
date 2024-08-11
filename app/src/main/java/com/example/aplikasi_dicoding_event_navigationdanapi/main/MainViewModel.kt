package com.example.aplikasi_dicoding_event_navigationdanapi.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.SettingPreferences

class MainViewModel(private val pref: SettingPreferences): ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }
}