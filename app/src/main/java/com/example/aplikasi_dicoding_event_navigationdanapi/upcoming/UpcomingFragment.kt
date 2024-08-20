package com.example.aplikasi_dicoding_event_navigationdanapi.upcoming

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.ui.EventAdapter
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.FragmentUpcomingBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.detail.DetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment : Fragment() {
    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<UpcomingVIewModel>()

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

        initViewModel()

    }

    private fun initViewModel() {
        viewModel.getEvents().observe(viewLifecycleOwner) { apiResult ->
            if (apiResult != null) {
                when (apiResult) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }

                    is Resource.Error<*> -> {
                        showLoading(false)
                        Toast.makeText(context, apiResult.error, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        showLoading(false)
                    }
                }
            }

        }
        viewModel.listEventItem.observe(viewLifecycleOwner) { result ->
            setEventDataRecyclerView(result)
        }
    }

    private fun setEventDataRecyclerView(consumerReviews: List<Events>) {
        val adapter = EventAdapter()
        adapter.submitList(consumerReviews)
        binding.apply {
            rvEvent.setHasFixedSize(true)
            rvEvent.layoutManager = LinearLayoutManager(requireActivity())
            rvEvent.adapter = adapter

            checkIfEmpty(rvEvent, emptyView)
        }

        adapter.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Events) {
                Intent(requireActivity(), DetailsActivity::class.java).also { intent ->
                    intent.putExtra(DetailsActivity.EXTRA_DATA, data)
                    requireActivity().startActivity(intent)
                }
            }
        })
    }

    private fun checkIfEmpty(recyclerView: RecyclerView, emptyView: TextView) {
        val adapter = recyclerView.adapter
        if (adapter != null) {
            if (adapter.itemCount == 0) {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
            }
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