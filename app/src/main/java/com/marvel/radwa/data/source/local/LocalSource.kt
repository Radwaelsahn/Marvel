package com.marvel.radwa.data.source.local

import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

interface LocalSource {

    fun saveCharacter(character: Character)
    fun getAllCharacters(): List<Character>

    fun saveComic(comics: Comics)
    fun getComicById(id: Int): List<Comics>

}
