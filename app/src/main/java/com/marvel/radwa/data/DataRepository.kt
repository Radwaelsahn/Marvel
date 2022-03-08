package com.marvel.radwa.data

import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.data.entities.responses.BaseResponse
import com.marvel.radwa.data.local.LocalRepository
import com.marvel.radwa.data.remote.RemoteRepository
import javax.inject.Inject


/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
) : DataSource {
    override fun saveCharacter(character: Character) {
        localRepository.saveCharacter(character)
    }

    override fun getAllCharacters(): List<Character> {
        return localRepository.getAllCharacters()
    }

    override fun saveComic(comics: Comics) {
        localRepository.saveComic(comics)
    }

    override fun getComicsByCharacterId(id: Int): List<Comics> {
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