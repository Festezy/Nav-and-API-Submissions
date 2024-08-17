package com.example.aplikasi_dicoding_event_navigationdanapi.di

import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsInteractor
import com.example.aplikasi_dicoding_event_navigationdanapi.core.domain.usecase.EventsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(eventsInteractor: EventsInteractor): EventsUseCase

}