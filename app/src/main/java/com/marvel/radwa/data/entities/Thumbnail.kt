package com.marvel.radwa.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "thumbnails.db")
@Parcelize
data class Thumbnail(
    @ColumnInfo(name = "extension") val extension: String,
    @ColumnInfo(name = "path") val path: String,
    @PrimaryKey(autoGenerate = true)
    var dbId: Int = 0
) : Parcelable