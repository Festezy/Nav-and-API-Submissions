package com.example.aplikasi_dicoding_event_navigationdanapi.di

import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsInteractor
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideTourismUseCase(eventsInteractor: EventsInteractor): EventsUseCase

}