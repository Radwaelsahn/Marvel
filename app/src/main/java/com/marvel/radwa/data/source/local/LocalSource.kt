package com.marvel.radwa.data.source.local

import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

interface LocalSource {

    suspend fun saveCharacter(character: Character)
    suspend fun getAllCharacters(): List<Character>

    suspend fun saveComic(comics: Comics)
    suspend fun getComicById(id: Int): List<Comics>

}
