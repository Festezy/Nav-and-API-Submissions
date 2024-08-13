package com.example.aplikasi_dicoding_event_navigationdanapi.core.utils

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem

object DataMapper {
    fun mapResponsesToEntities(input: List<ListEventsItem>): List<EventEntity> {
        val eventList = ArrayList<EventEntity>()
        input.map {
            val event = EventEntity(
                id = it.id.toString(),
                name = it.name!!,
                mediaCover = it.mediaCover,
                isFavorite = false
            )
            eventList.add(event)
        }
        return eventList
    }
}