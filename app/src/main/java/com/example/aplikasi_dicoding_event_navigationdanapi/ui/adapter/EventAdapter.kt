package com.example.aplikasi_dicoding_event_navigationdanapi.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.Size
import com.example.aplikasi_dicoding_event_navigationdanapi.R
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.databinding.ItemEventBinding
import com.example.aplikasi_dicoding_event_navigationdanapi.detail.DetailsActivity

class EventAdapter : ListAdapter<EventEntity, EventAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(eventItem: EventEntity) {
            binding.apply {
                imgItemPhoto.load(eventItem.mediaCover)
                {
                    placeholder(R.drawable.ic_loading)
                    Size(250, 250)
                    Scale.FILL
                }
                tvItemName.text = eventItem.name

                root.setOnClickListener {
                    Intent(root.context, DetailsActivity::class.java).also { intent ->
                        intent.putExtra(DetailsActivity.EXTRA_ID, eventItem.id.toString())
                        it.context.startActivity(intent)
                    }
                }
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EventEntity>() {
            override fun areItemsTheSame(
                oldItem: EventEntity,
                newItem: EventEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: EventEntity,
                newItem: EventEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}