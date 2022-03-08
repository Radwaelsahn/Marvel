package com.marvel.radwa.data

import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.data.entities.responses.BaseResponse

/**
 * Created by Radwa Elsahn on 7/26/2020
 */

interface DataSource {
    /**local**/
    fun saveCharacter(character: Character)
    fun getAllCharacters(): List<Character>

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
