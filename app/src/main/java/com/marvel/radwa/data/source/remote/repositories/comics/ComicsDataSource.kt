package com.marvel.radwa.data.source.remote.repositories.comics

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse

/**
 * Created by Radwa Elsahn on 7/26/2020
 */

interface ComicsDataSource {
    /**local**/
    suspend fun saveComic(comics: Comics)
    suspend fun getComicsByCharacterId(id: Int): List<Comics>

    /**remote*/
    suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>>
}
