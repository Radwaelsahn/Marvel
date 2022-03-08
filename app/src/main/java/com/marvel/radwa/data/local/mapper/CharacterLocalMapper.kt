package com.marvel.radwa.data.local.mapper

import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.local.models.CharacterLocal
import com.marvel.radwa.data.local.utils.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterLocalMapper @Inject constructor() : Mapper<Character, CharacterLocal> {
    override fun from(model: CharacterLocal): Character {
        return Character(
            id = model.id,
            name = model.name,
            description = model.description,
            thumbnail = model.thumbnail
        )
    }

    override fun to(entity: Character): CharacterLocal {
        return CharacterLocal(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            thumbnail = entity.thumbnail
        )
    }

}
