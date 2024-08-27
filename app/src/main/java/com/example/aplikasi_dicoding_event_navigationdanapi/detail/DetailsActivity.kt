package com.example.aplikasi_dicoding_event_navigationdanapi.detail

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import com.example.aplikasi_dicoding_event_navigationdanapi.R
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

        showLoading(true)
        // data From intent
        val eventDetail = intent.getParcelableExtra<Events>(EXTRA_DATA)
        eventDetail.let { result ->
            getFavoriteData(result)
            initView(result)
        }
        showLoading(false)
    }

    private fun initView(result: Events?) {
        binding.apply {
            eventImage.load(result?.mediaCover)
            eventCategory.text = result?.category
            eventTitle.text = result?.name
            eventOrganizer.text = result?.ownerName
            eventDescriptions.text =
                result?.description?.let { convertHtmlToFormattedString(it) }

            eventExpired.text =
                result?.endTime?.let {
                    convertStringToFormattedString(it)
                }
            eventQuota.text = result?.quota.toString()

            eventRegisterButton.setOnClickListener {
                gotoUrl(
                    this@DetailsActivity,
                    result?.link
                )
            }
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

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}