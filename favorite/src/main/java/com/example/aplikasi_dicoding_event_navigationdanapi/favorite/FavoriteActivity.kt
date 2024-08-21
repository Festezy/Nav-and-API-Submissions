package com.example.aplikasi_dicoding_event_navigationdanapi.favorite

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.aplikasi_dicoding_event_navigationdanapi.di.FavoriteModuleDependencies
import com.example.aplikasi_dicoding_event_navigationdanapi.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setCurrentFragment(FavoriteFragment())
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_event -> {
//                    Intent(this@FavoriteActivity, MainActivity::class.java).also {
//                        this@FavoriteActivity.startActivity(it)
//                    }
////                    setCurrentFragment(UpcomingFragment())
//                    true
//                }
//
//                R.id.navigation_finish_event -> {
//                    Intent(this@FavoriteActivity, MainActivity::class.java).also {
//                        this@FavoriteActivity.startActivity(it)
//                    }
////                    setCurrentFragment(FinishEventFragment())
//                    true
//                }
//
//                R.id.navigation_favorite_event -> {
//                    val uri = Uri.parse("dicodingevent://favorite")
//                    startActivity(Intent(Intent.ACTION_VIEW, uri))
////                    setCurrentFragment()
//                    true
//                }
//
//                else -> false
//            }
//        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
    }
}