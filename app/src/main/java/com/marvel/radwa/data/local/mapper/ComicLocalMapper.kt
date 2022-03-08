package com.marvel.radwa.data.local.mapper

import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.data.local.models.ComicsLocal
import com.marvel.radwa.data.local.utils.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComicLocalMapper @Inject constructor() : Mapper<Comics, ComicsLocal> {
    override fun from(model: ComicsLocal): Comics {
        return Comics(
            id = model.id,
            characterId = model.characterId,
            title = model.title,
            thumbnail = model.thumbnail
        )
    }

    override fun to(entity: Comics): ComicsLocal {
        return ComicsLocal(
            id = entity.id,
            characterId = entity.characterId,
            title = entity.title,
            thumbnail = entity.thumbnail
        )
    }

}
