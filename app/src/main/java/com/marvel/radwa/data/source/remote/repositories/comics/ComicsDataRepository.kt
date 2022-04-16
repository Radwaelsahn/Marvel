package com.marvel.radwa.data.source.remote.repositories.comics

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.source.local.LocalSource
import com.marvel.radwa.data.source.remote.repositories.characters.CharactersDataSource
import com.marvel.radwa.data.source.remote.repositories.characters.CharactersRemoteRepository
import javax.inject.Inject


/**
 * Created by Radwa Elsahn on 7/7/2020
 */
class ComicsDataRepository @Inject constructor(
    private val remoteRepository: ComicsRemoteRepository,
    private val localRepository: LocalSource
) : ComicsDataSource {


    override suspend fun saveComic(comics: Comics) {
        localRepository.saveComic(comics)
    }

    override suspend fun getComicsByCharacterId(id: Int): List<Comics> {
        return localRepository.getComicById(id)
    }

    /**Local**/

    /**    remote **/

    override suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>> {
        return remoteRepository.getCharacterComics(characterId, ts, apikey, hash)
    }
}