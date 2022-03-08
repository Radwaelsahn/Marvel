package com.marvel.radwa.di


import android.app.Application
import android.content.Context
import com.marvel.radwa.data.DataRepository
import com.marvel.radwa.data.DataSource
import com.marvel.radwa.data.local.db.CharactersDao
import com.marvel.radwa.data.local.db.MarvelDatabase
import com.marvel.radwa.data.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun provideDataRepository(dataRepository: DataRepository): DataSource

//    @Binds
//    @Singleton
//    abstract fun providesDatabase(db: MarvelDatabase): MarvelDatabase
//
//    @Binds
//    @Singleton
//    abstract fun providesCharactersDAO(dao: CharactersDao): CharactersDao

//
//    @Provides
//    fun provideLocalRepository(): LocalSource {
//        return LocalSource()
//    }


//    @Binds
//    @Singleton
//    abstract fun provideCharacterLocalMapper(mapper: CharacterLocalMapper): CharacterLocalMapper
//
//    @Binds
//    @Singleton
//    abstract fun provideCharactersDao(dao: CharactersDao): CharactersDao

//    @Binds
//    abstract fun provideLocalRepository(
//        mapper: CharacterLocalMapper
//    ): LocalRepository

//    @Binds
//    abstract fun provideLocalRepository1(
//        characterDao: CharactersDao
//    ): LocalRepository


//    @Binds
//    abstract fun provideLocalMapper(mapper: CharacterLocalMapper): CharacterLocalMapper

    //    @Binds
//    abstract fun provideLocalMapper(mapper: CharacterLocalMapper): CharacterLocalMapper


}