package com.marvel.radwa.di


import android.app.Application
import android.content.Context
import com.marvel.radwa.data.source.DataRepository
import com.marvel.radwa.data.source.DataSource
import com.marvel.radwa.data.source.local.db.MarvelDatabase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun provideDataRepository(dataRepository: DataRepository): DataSource

    @Binds
    abstract fun provideMarvelDatabase(db: MarvelDatabase): MarvelDatabase

}