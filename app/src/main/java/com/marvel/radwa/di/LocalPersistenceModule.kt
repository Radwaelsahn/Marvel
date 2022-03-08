//package com.marvel.radwa.di
//
//import android.app.Application
//import com.marvel.radwa.data.local.LocalRepository
//import com.marvel.radwa.data.local.LocalSource
//import com.marvel.radwa.data.local.db.MarvelDatabase
//import com.marvel.radwa.data.local.mapper.CharacterLocalMapper
//import com.sunragav.indiecampers.home.data.repository.LocalRepository
//import com.sunragav.indiecampers.localdata.datasource.LocalDataSourceImpl
//import com.sunragav.indiecampers.localdata.db.ComicsDB
//import dagger.Binds
//import dagger.Module
//import dagger.Provides
//import javax.inject.Singleton
//
//@Module(includes = [LocalPersistenceModule.Binders::class])
//class LocalPersistenceModule {
//
//    @Module
//    interface Binders {
//
//        @Binds
//        fun bindsLocalDataSource(
//            localDataSourceImpl: LocalRepository
//        ): LocalSource
//
//    }
//
//    @Provides
//    @Singleton
//    fun providesDatabase(
//        application: Application
//    ) = MarvelDatabase.getInstance(application.applicationContext)
//
//    @Provides
//    @Singleton
//    fun providesComicsDAO(
//        comicsDB: MarvelDatabase
//    ) = comicsDB.CharactersDao()
//
//    @Provides
//    @Singleton
//    fun providesCharacterLocalMapper(mapper: CharacterLocalMapper) = CharacterLocalMapper()
//
//}
