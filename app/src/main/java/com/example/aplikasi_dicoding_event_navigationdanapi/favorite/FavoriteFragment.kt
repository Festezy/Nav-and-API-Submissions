package com.example.aplikasi_dicoding_event_navigationdanapi.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.FragmentFavoriteBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.finish.FinishEventViewModel
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter.EventAdapter
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter.ViewModelFactory
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoriteViewModel>{
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFavoriteEvents()
        viewModel.listFavoriteEvents.observe(viewLifecycleOwner){ favoriteEventList ->
            setFavoriteEventData(favoriteEventList)
        }
    }

    private fun setFavoriteEventData(eventData: List<EventEntity>) {
        val adapter = EventAdapter()
        adapter.submitList(eventData)
        binding.apply {
            rvEvent.setHasFixedSize(true)
            rvEvent.layoutManager = LinearLayoutManager(requireActivity())
            rvEvent.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        }
    }
}