package com.example.aplikasi_dicoding_event_navigationdanapi.finish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.FragmentFinishEventBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.core.ui.EventAdapter
import com.example.aplikasi_dicoding_event_navigationdanapi.core.ui.ViewModelFactory

class FinishEventFragment : Fragment() {
    private var _binding: FragmentFinishEventBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FinishEventViewModel>{
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFinishEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val viewModel = ViewModelProvider(
//            requireActivity(),
//            ViewModelProvider.NewInstanceFactory()
//        )[FinishEventViewModel::class.java]
//        viewModel.getFinishedEventList("0")
//        viewModel.listEventItem.observe(viewLifecycleOwner) { item ->
//            setEventData(item)
//        }
//        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
//            showLoading(isLoading)
//        }

        viewModel.getEvents().observe(viewLifecycleOwner){ apiResult ->
            if (apiResult != null){
                when(apiResult){
                    is Resource.Loading -> {
                        showLoading(true)
                    }
                    is Resource.Error<*> -> {
                        showLoading(false)
                        Toast.makeText(context, apiResult.error, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
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
            rvEvent.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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