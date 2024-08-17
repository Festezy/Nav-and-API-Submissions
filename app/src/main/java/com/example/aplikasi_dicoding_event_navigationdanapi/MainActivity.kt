package com.example.aplikasi_dicoding_event_navigationdanapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ActivityMainBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.favorite.FavoriteFragment
import com.example.aplikasi_dicoding_event_navigationdanapi.finish.FinishEventFragment
import com.example.aplikasi_dicoding_event_navigationdanapi.upcoming.UpcomingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

                R.id.navigation_favorite_event -> {
                    setCurrentFragment(FavoriteFragment())
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