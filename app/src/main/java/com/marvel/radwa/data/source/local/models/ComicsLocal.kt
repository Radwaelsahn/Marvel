package com.marvel.radwa.data.source.local.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marvel.radwa.data.models.Thumbnail

@Entity(tableName = "comics.db")
data class ComicsLocal(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "characterId") val characterId: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @Embedded val thumbnail: Thumbnail?
)