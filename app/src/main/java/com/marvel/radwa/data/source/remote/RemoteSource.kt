package com.marvel.radwa.data.source.remote

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

interface RemoteSource {
    suspend fun getMarvelCharacters(
        ts: String,
        apikey: String,
        hash: String,limit: Int, offset: Int
    ): Resource<BaseResponse<Character>>

    suspend fun getCharacterDetails(
        characterId: Int, ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Character>>

    suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>>
}