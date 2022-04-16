package com.marvel.radwa.data.source.remote.repositories.comics

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

interface ComicsRemoteSource {

    suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>>
}
