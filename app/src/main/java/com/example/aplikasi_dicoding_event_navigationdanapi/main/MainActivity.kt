package com.example.aplikasi_dicoding_event_navigationdanapi.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ActivityMainBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.finish.FinishEventFragment
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter.EventAdapter
import com.example.aplikasi_dicoding_event_navigationdanapi.upcoming.UpcomingFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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