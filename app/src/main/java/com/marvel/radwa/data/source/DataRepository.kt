package com.marvel.radwa.data.source

import com.marvel.radwa.data.Resource
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.source.local.LocalRepository
import com.marvel.radwa.data.source.local.LocalSource
import com.marvel.radwa.data.source.remote.RemoteRepository
import com.marvel.radwa.data.source.remote.RemoteSource
import javax.inject.Inject


/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteSource,
    private val localRepository: LocalSource,
//    private val remoteRepository: RemoteRepository,
//    private val localRepository: LocalRepository,
) : DataSource {
    override suspend fun saveCharacter(character: Character) {
        localRepository.saveCharacter(character)
    }

    override suspend  fun getAllCharacters(): List<Character> {
        return localRepository.getAllCharacters()
    }

    override suspend  fun saveComic(comics: Comics) {
        localRepository.saveComic(comics)
    }

    override suspend fun getComicsByCharacterId(id: Int): List<Comics> {
        return localRepository.getComicById(id)
    }

    /**Local**/

    /**    remote **/
    override suspend fun getMarvelCharacters(
        ts: String,
        apikey: String,
        hash: String, limit: Int, offset: Int
    ): Resource<BaseResponse<Character>> {
        return remoteRepository.getMarvelCharacters(ts, apikey, hash, limit, offset)
    }

    override suspend fun getCharacterDetails(
        characterId: Int, ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Character>> {
        return remoteRepository.getCharacterDetails(characterId, ts, apikey, hash)
    }

    override suspend fun getCharacterComics(
        characterId: Int,
        ts: String,
        apikey: String,
        hash: String
    ): Resource<BaseResponse<Comics>> {
        return remoteRepository.getCharacterComics(characterId, ts, apikey, hash)
    }
}