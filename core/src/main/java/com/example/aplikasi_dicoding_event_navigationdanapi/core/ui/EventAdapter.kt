package com.example.aplikasi_dicoding_event_navigationdanapi.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.Size
import com.example.aplikasi_dicoding_event_navigationdanapi.core.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.databinding.ItemEventBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events

class EventAdapter : ListAdapter<Events, EventAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class MyViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventItem: Events) {
            binding.apply {
                imgItemPhoto.load(eventItem.mediaCover)
                {
                    placeholder(R.drawable.ic_error)
                    Size(250, 250)
                    Scale.FILL
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
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(getItem(holder.adapterPosition)) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Events)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Events>() {
            override fun areItemsTheSame(
                oldItem: Events,
                newItem: Events
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Events,
                newItem: Events
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}