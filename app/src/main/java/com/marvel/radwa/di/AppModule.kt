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

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.marvel.radwa.App
import com.marvel.radwa.data.source.local.LocalRepository
import com.marvel.radwa.data.source.local.LocalSource
import com.marvel.radwa.data.source.local.Session
import com.marvel.radwa.data.source.local.SharedPrefHelper
import com.marvel.radwa.data.source.local.mapper.CharacterLocalMapper
import com.marvel.radwa.data.source.local.mapper.ComicsLocalMapper
import com.marvel.radwa.data.source.remote.networking.ServiceGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun provideApplication(application: Application): App = application as App

    @Singleton
    @Provides
    fun provideSharedPrefHelper(@ApplicationContext context: Context): SharedPrefHelper = SharedPrefHelper(context)

    @Singleton
    @Provides
    fun provideSession(sharedPrefHelper: SharedPrefHelper): Session = Session(sharedPrefHelper)

    @Singleton
    @Provides
    fun provideServiceGenerator(session: Session): ServiceGenerator = ServiceGenerator(session) // TODO Remove after refactor

    @Provides
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }


    @Provides
    @Singleton
    fun provideLocalSource(
        context: Context,
        mapper: CharacterLocalMapper,
        mapper2: ComicsLocalMapper
    ): LocalSource {
        return LocalRepository(context,mapper,mapper2)
    }

}
