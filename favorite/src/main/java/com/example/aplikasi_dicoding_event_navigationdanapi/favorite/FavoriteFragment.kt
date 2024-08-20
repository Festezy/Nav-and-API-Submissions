package com.example.aplikasi_dicoding_event_navigationdanapi.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasi_dicoding_event_navigationdanapi.MyApplication
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.ui.EventAdapter
import com.example.aplikasi_dicoding_event_navigationdanapi.detail.DetailsActivity
import com.example.aplikasi_dicoding_event_navigationdanapi.favorite.databinding.FragmentFavoriteBinding
import javax.inject.Inject

//@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

//    private val viewModel by viewModels<FavoriteViewModel>()
    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels {
        factory
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        Dagger
//        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading(true)
        viewModel.getFavoriteEvents()
        viewModel.listFavoriteEvents.observe(viewLifecycleOwner) { favoriteEventList ->
            setFavoriteEventData(favoriteEventList)
        }
        showLoading(false)
    }

    private fun setFavoriteEventData(eventData: List<Events>) {
        val adapter = EventAdapter()
        adapter.submitList(eventData)
        binding.apply {
            rvEvent.setHasFixedSize(true)
            rvEvent.layoutManager = LinearLayoutManager(requireActivity())
            rvEvent.adapter = adapter
        }
        adapter.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Events) {
                Intent(requireActivity(), DetailsActivity::class.java).also { intent ->
                    intent.putExtra(DetailsActivity.EXTRA_ID, data.id)
                    intent.putExtra(DetailsActivity.EXTRA_DATA, data)
                    requireActivity().startActivity(intent)
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}