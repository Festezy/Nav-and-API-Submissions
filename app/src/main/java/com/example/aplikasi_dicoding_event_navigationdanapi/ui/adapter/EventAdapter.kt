package com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ItemEventBinding

class EventAdapter: ListAdapter<ListEventsItem, EventAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(eventItem: ListEventsItem){
            binding.apply {
                imgItemPhoto.load(eventItem.imageLogo) {
                    crossfade(true)
                    placeholder(R.drawable.ic_loading)
                    transformations(RoundedCornersTransformation())
                }
                tvItemName.text = eventItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val eventItem = getItem(position)
        holder.bind(eventItem)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>() {
            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}