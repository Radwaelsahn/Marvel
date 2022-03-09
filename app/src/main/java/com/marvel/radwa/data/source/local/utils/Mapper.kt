package com.marvel.radwa.data.source.local.utils

interface Mapper<Entity, Model> {

    fun from(model: Model): Entity

    fun to(entity: Entity): Model

}
