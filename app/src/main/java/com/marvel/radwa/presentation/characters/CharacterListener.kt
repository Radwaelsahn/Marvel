package com.marvel.radwa.presentation.characters

import com.marvel.radwa.data.entities.Character


interface CharacterListener {
    fun onCharacterClicked(character: Character)
}