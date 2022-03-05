package com.marvel.radwa.di


import com.marvel.radwa.data.DataRepository
import com.marvel.radwa.data.DataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun provideDataRepository(dataRepository: DataRepository): DataSource

}