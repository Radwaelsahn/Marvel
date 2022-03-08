package com.marvel.radwa.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marvel.radwa.data.entities.Thumbnail

@Entity(tableName = "characters.db")
data class CharacterLocal(
    @PrimaryKey(autoGenerate = true) val character_unique_id: Int,
    @ColumnInfo(name = "refId") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,

    @Embedded val thumbnail: Thumbnail
)
