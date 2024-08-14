package com.example.aplikasi_dicoding_event_navigationdanapi.core.utils

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.local.entity.EventEntity
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.source.remote.response.ListEventsItem
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events

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

    fun mapEntitiesToDomain(input: List<EventEntity>): List<Events> =
        input.map {
            Events(
                id = it.id,
                name = it.name,
                mediaCover = it.mediaCover,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: Events) = EventEntity(
        id = input.id,
        name = input.name,
        mediaCover = input.mediaCover,
        isFavorite = input.isFavorite
    )
}