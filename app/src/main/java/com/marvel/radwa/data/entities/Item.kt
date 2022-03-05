package com.marvel.radwa.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val name: String,
    val resourceURI: String,
    val role: String?
):Parcelable