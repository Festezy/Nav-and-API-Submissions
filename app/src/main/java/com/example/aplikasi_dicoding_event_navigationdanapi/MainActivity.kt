package com.example.aplikasi_dicoding_event_navigationdanapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_upcoming,
                R.id.navigation_finished
//                R.id.navigation_favorite_event
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        setCurrentFragment(UpcomingFragment())
//
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_event -> {
                    navController.navigate(R.id.navigation_upcoming)
//                    setCurrentFragment(UpcomingFragment())
                    true
                }

                R.id.navigation_finish_event -> {
                    navController.navigate(R.id.navigation_finished)
//                    setCurrentFragment(FinishEventFragment())
                    true
                }

//                R.id.navigation_favorite_event -> {
//                    val uri = Uri.parse("dicodingevent://favorite")
//                    startActivity(Intent(Intent.ACTION_VIEW, uri))
////                    setCurrentFragment()
//                    true
//                }

                else -> false
            }
        }
    }

//    private fun setCurrentFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.nav_host_fragment, fragment)
//            commit()
//        }
//    }
}