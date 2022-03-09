/*
 * Copyright (C) 2019 The Android Open Source Project
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

package com.marvel.radwa.data.source

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.models.responses.ErrorResponse
import com.marvel.radwa.data.source.remote.RemoteSource

//class FakeDataSource(var characters: MutableList<Character> = mutableListOf()) : DataSource {
class FakeRemoteSource(var charactersResponseStr: String) : RemoteSource {


    override suspend fun getMarvelCharacters(
        ts: String,
        apikey: String,
        hash: String,
        limit: Int,
        offset: Int
    ): Resource<BaseResponse<Character>> {


//        characters?.let {
//
//            val response = BaseResponse(
//                200, "", "", "", "", "",
//                Data(0, 20, 1000, 1000, characters)
//            )

        return try {
            var myResponse = charactersResponseStr as BaseResponse<Character>
            Resource.Success(data = myResponse)
        } catch (e: Exception) {
            Resource.DataError(errorResponse = ErrorResponse("ERORR"))
        }

//        }
//        val charactersResponseApiPath= "characters/CharactersApiResponse.json"
//        val response = getJson(charactersResponseApiPath)
//
//        return try {
//            var myResponse = response as BaseResponse<Character>
//            Resource.Success(data = myResponse)
//        } catch (e: Exception) {
//            Resource.DataError(errorResponse = response as ErrorResponse)
//        }

    }

    override suspend fun getCharacterDetails(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Character>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>> {
        TODO("Not yet implemented")
    }


}