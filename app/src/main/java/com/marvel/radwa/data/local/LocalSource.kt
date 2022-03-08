package com.marvel.radwa.data.local

import com.marvel.radwa.data.entities.Character

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

internal interface LocalSource {

    fun saveCharacter(character: Character)
    fun getAllCharacters(): List<Character>

}
