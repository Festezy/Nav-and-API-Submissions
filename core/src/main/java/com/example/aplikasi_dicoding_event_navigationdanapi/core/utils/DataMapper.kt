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
                registrants = it.registrants,
                summary = it.summary,
                imageLogo = it.imageLogo,
                link = it.link,
                description = it.description,
                ownerName = it.ownerName,
                cityName = it.cityName,
                quota = it.quota,
                beginTime = it.beginTime,
                endTime = it.endTime,
                category = it.category,
                isExpired = isDatePassed(it.endTime.toString()),
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
                registrants = it.registrants,
                summary = it.summary,
                imageLogo = it.imageLogo,
                link = it.link,
                description = it.description,
                ownerName = it.ownerName,
                cityName = it.cityName,
                quota = it.quota,
                beginTime = it.beginTime,
                endTime = it.endTime,
                category = it.category,
                isExpired = it.isExpired,
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: Events) = EventEntity(
        id = input.id,
        name = input.name,
        mediaCover = input.mediaCover,
        registrants = input.registrants,
        summary = input.summary,
        imageLogo = input.imageLogo,
        link = input.link,
        description = input.description,
        ownerName = input.ownerName,
        cityName = input.cityName,
        quota = input.quota,
        beginTime = input.beginTime,
        endTime = input.endTime,
        category = input.category,
        isExpired = input.isExpired,
        isFavorite = input.isFavorite
    )
}