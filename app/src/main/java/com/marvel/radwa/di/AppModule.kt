/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marvel.radwa.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marvel.radwa.data.source.local.LocalRepository
import com.marvel.radwa.data.source.local.LocalSource
import com.marvel.radwa.data.source.local.mapper.CharacterLocalMapper
import com.marvel.radwa.data.source.local.mapper.ComicsLocalMapper
import com.marvel.radwa.data.source.remote.RemoteRepository
import com.marvel.radwa.data.source.remote.RemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

    @Provides
    fun provideRemoteSource(): RemoteSource {
        return RemoteRepository()
    }


    @Provides
    fun provideLocalSource(
        context: Context,
        mapper: CharacterLocalMapper,
        mapper2: ComicsLocalMapper
    ): LocalSource {
        return LocalRepository(context,mapper,mapper2)
    }

//    @Provides
//    @Singleton
//    fun providesDatabase(
//        application: Application
//    ) = MarvelDatabase.getInstance(application.applicationContext)
//
//
//    @Provides
//    @Singleton
//    fun providesCharactersDAO(
//        db: MarvelDatabase
//    ) = db.CharactersDao()

////////////////////////////////////
//    @Provides
//    @Singleton
//    fun providesCharacterLocalMapper(mapper: CharacterLocalMapper) = CharacterLocalMapper()
}
