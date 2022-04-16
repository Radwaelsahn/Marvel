package com.marvel.radwa.di

import com.marvel.radwa.data.source.remote.repositories.characters.CharactersDataRepository
import com.marvel.radwa.data.source.remote.repositories.characters.CharactersDataSource
import com.marvel.radwa.data.source.remote.repositories.comics.ComicsDataRepository
import com.marvel.radwa.data.source.remote.repositories.comics.ComicsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharactersDataRepository(dataRepository: CharactersDataRepository): CharactersDataSource


    @Binds
    abstract fun provideComicsDataRepository(dataRepository: ComicsDataRepository): ComicsDataSource
}