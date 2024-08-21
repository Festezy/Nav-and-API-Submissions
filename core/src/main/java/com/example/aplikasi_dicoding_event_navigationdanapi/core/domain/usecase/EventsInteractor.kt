package com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase

import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.repository.IEventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EventsInteractor @Inject constructor(private val eventsRepository: IEventsRepository) :
    EventsUseCase {
    override fun getEvents(): Flow<Resource<List<Events>>> =
        eventsRepository.getEvents()

    override fun getFavoriteEvent(): Flow<List<Events>> =
        eventsRepository.getFavoriteEvent()

    override fun getUpcomingEvents(): Flow<List<Events>> =
        eventsRepository.getUpcomingEvents()

    override fun getFinishedEvents(): Flow<List<Events>> =
        eventsRepository.getFinishedEvents()

    override fun setFavoriteEvent(events: Events, favoriteState: Boolean) =
        eventsRepository.setFavoriteEvent(events, favoriteState)
}