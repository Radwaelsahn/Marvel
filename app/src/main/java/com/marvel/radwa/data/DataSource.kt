package com.marvel.radwa.data

import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.data.entities.responses.BaseResponse
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Radwa Elsahn on 7/26/2020
 */

interface DataSource {
    /**local**/
//    fun saveUser(dataLogin: DataUser)

    /**remote*/
    suspend fun getMarvelCharacters(
        ts: String,
        apikey: String,
        hash: String, limit: Int, offset: Int
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
