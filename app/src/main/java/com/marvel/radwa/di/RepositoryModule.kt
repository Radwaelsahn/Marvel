package com.marvel.radwa.di

import com.marvel.radwa.data.source.DataRepository
import com.marvel.radwa.data.source.DataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideLoginDataRepository(dataRepository: DataRepository): DataSource
}