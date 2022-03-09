package com.marvel.radwa.presentation.characters

import com.marvel.radwa.data.models.Character


interface CharacterListener {
    fun onCharacterClicked(character: Character)
}