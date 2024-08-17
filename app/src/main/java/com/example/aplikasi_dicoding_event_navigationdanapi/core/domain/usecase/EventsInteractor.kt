package com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.aplikasi_dicoding_event_navigationdanapi.core.data.Resource
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.model.Events
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.repository.IEventsRepository
import kotlinx.coroutines.flow.Flow

class EventsInteractor(private val eventsRepository: IEventsRepository): EventsUseCase {
    override fun getEvents(): Flow<Resource<List<Events>>> =
        eventsRepository.getEvents()
    override fun getFavoriteEvent(): Flow<List<Events>> =
        eventsRepository.getFavoriteEvent()
    override fun setFavoriteEvent(events: Events, favoriteState: Boolean) =
        eventsRepository.setFavoriteEvent(events, favoriteState)
}