package com.example.aplikasi_dicoding_event_navigationdanapi.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ActivityMainBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.favorite.FavoriteFragment
import com.example.aplikasi_dicoding_event_navigationdanapi.finish.FinishEventFragment
import com.example.aplikasi_dicoding_event_navigationdanapi.setting.SettingFragment
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter.ViewModelFactory
import com.example.aplikasi_dicoding_event_navigationdanapi.upcoming.UpcomingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val pref = SettingPreferences.getInstance(application.dataStore)
//        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
//            MainViewModel::class.java
//        )

        viewModel.getThemeSettings().observe(this@MainActivity) { isDarkMode ->
            if (isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        setCurrentFragment(UpcomingFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_event -> {
                    setCurrentFragment(UpcomingFragment())
                    true
                }

                R.id.navigation_finish_event -> {
                    setCurrentFragment(FinishEventFragment())
                    true
                }

                R.id.navigation_favorite_event -> {
                    setCurrentFragment(FavoriteFragment())
                    true
                }

                R.id.navigation_setting -> {
                    setCurrentFragment(SettingFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
    }
}