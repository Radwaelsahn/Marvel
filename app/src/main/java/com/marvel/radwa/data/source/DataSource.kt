package com.marvel.radwa.data.source

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse

/**
 * Created by Radwa Elsahn on 7/26/2020
 */

interface DataSource {
    /**local**/
    fun saveCharacter(character: Character)
    fun getAllCharacters(): List<Character>
    fun saveComic(comics: Comics)
    fun getComicsByCharacterId(id: Int): List<Comics>

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