package com.example.aplikasi_dicoding_event_navigationdanapi.detail

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.convertHtmlToFormattedString
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.convertStringToFormattedString
import com.example.aplikasi_dicoding_event_navigationdanapi.core.utils.gotoUrl
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ActivityDetailsBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter.ViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private val viewModel by viewModels<DetailsViewModel>() {
        ViewModelFactory.getInstance(application)
    }

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
        val eventEntity = intent.getParcelableExtra<EventEntity>(EXTRA_DATA)
        eventId.let {
            viewModel.getDetailData(it!!)
        }
        getFavoriteData(eventEntity)

        viewModel.listEventDetail.observe(this) { result ->
            binding.apply {
                eventImage.load(result.mediaCover)
                eventCategory.text = result.category
                eventTitle.text = result.name
                eventOrganizer.text = result.ownerName
                eventDescriptions.text =
                    result.description?.let { convertHtmlToFormattedString(it) }

                eventExpired.text = result.endTime?.let { convertStringToFormattedString(it) }
                eventQuota.text = result.quota.toString()

                eventRegisterButton.setOnClickListener {
                    gotoUrl(
                        this@DetailsActivity,
                        result.link
                    )
                }
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun getFavoriteData(favorite: EventEntity?) {
        favorite?.let {
            var statusFavorite = favorite.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.saveFavorite(favorite)
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

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DATA = "extra_data"
    }
}