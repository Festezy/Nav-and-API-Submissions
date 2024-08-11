package com.example.aplikasi_dicoding_event_navigationdanapi.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.SettingPreferences
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferences: SettingPreferences): ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return preferences.getThemeSetting().asLiveData()
    }
    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            preferences.saveThemeSetting(isDarkModeActive)
        }
    }
}