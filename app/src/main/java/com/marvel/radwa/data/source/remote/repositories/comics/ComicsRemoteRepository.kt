package com.marvel.radwa.data.source.remote.repositories.comics

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.models.responses.ErrorResponse
import com.marvel.radwa.data.source.remote.BaseRemoteRepository
import com.marvel.radwa.data.source.remote.networking.ServiceGenerator
import com.marvel.radwa.data.source.remote.repositories.characters.CharactersService
import javax.inject.Inject

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class ComicsRemoteRepository @Inject constructor(val service: CharacterComicsService) : ComicsRemoteSource, BaseRemoteRepository() {

    override suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>> {
//        val service = ServiceGenerator.createService(CharacterComicsService::class.java)
        val response = processCall { service.getCharacterComics(characterId, ts, apikey, hash) }

        return try {
            var myResponse = response as BaseResponse<Comics>
            Resource.Success(data = myResponse)
        } catch (e: Exception) {
            Resource.DataError(errorResponse = response as ErrorResponse)
        }
    }


}