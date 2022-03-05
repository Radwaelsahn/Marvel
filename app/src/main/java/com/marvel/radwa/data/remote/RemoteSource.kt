package com.marvel.radwa.data.remote

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.data.entities.responses.BaseResponse

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

internal interface RemoteSource {
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
