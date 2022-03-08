package com.marvel.radwa.data.local

import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

internal interface LocalSource {

    fun saveCharacter(character: Character)
    fun getAllCharacters(): List<Character>

    fun saveComic(comics: Comics)
    fun getComicById(id: Int): List<Comics>

}
