package com.example.aplikasi_dicoding_event_navigationdanapi.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.network.ApiResponse
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.convertHtmlToFormattedString
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.convertStringToFormattedString
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.gotoUrl
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // data From intent
        val eventId = intent.getStringExtra(EXTRA_ID)
        val eventEntity = intent.getParcelableExtra<Events>(EXTRA_DATA)
        eventId.let { id ->
            viewModel.getEventDetail(id!!)
        }
        getFavoriteData(eventEntity)

        viewModel.listEventDetail.observe(this@DetailsActivity) { result ->
            when (result) {
                is ApiResponse.Empty -> showLoading(true)
                is ApiResponse.Error -> {
                    showLoading(false)
                    showToast(result.errorMessage)
                    Log.d(TAG, "Error Message: ${result.errorMessage}")
                }

                is ApiResponse.Success -> {
                    showLoading(false)
                    binding.apply {
                        eventImage.load(result.data.mediaCover)
                        eventCategory.text = result.data.category
                        eventTitle.text = result.data.name
                        eventOrganizer.text = result.data.ownerName
                        eventDescriptions.text =
                            result.data.description?.let { convertHtmlToFormattedString(it) }

                        eventExpired.text =
                            result.data.endTime?.let { convertStringToFormattedString(it) }
                        eventQuota.text = result.data.quota.toString()

                        eventRegisterButton.setOnClickListener {
                            gotoUrl(
                                this@DetailsActivity,
                                result.data.link
                            )
                        }
                    }
                }
            }

        }

        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun getFavoriteData(favorite: Events?) {
        favorite?.let {
            var statusFavorite = favorite.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.saveFavorite(favorite, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.apply {
            if (statusFavorite) {
                fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailsActivity,
                        R.drawable.baseline_favorite_fill_24
                    )
                )
            } else {
                fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@DetailsActivity,
                        R.drawable.baseline_favorite_border_24
                    )
                )
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this@DetailsActivity, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "DetailsActivity"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DATA = "extra_data"
    }
}