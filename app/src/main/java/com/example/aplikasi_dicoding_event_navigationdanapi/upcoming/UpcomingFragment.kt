package com.example.aplikasi_dicoding_event_navigationdanapi.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.ApiResult
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.FragmentUpcomingBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter.EventAdapter
import com.example.aplikasi_dicoding_event_navigationdanapi.ui.ViewModelFactory

class UpcomingFragment : Fragment() {
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UpcomingVIewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEvents().observe(viewLifecycleOwner){ apiResult ->
            if (apiResult != null){
                when(apiResult){
                    is ApiResult.Loading -> {
                        showLoading(true)
                    }
                    is ApiResult.Error -> {
                        showLoading(false)
                        Toast.makeText(context, apiResult.error, Toast.LENGTH_SHORT).show()
                    }
                    is ApiResult.Success -> {
                        showLoading(false)
                        val eventData = apiResult.data
                        setEventData(eventData)
                    }
                }
            }

        }
    }

    private fun setEventData(consumerReviews: List<EventEntity>) {
        val adapter = EventAdapter()
        adapter.submitList(consumerReviews)
        binding.apply {
            rvEvent.setHasFixedSize(true)
            rvEvent.layoutManager = LinearLayoutManager(requireActivity())
            rvEvent.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}