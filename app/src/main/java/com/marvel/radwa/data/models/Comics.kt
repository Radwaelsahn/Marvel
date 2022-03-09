package com.marvel.radwa.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comics(
    val id:Int,
    var characterId: Int?,
    val title: String?,
    val thumbnail: Thumbnail?

//    val available: Int,
//    val collectionURI: String,
//    val items: List<Item>,
//    val returned: Int,
//    val characters: Characters,
//    val collectedIssues: List<Any>,
//    val collections: List<Any>,
//    val creators: Creators,
//    val dates: List<DateX>,
//    val description: String,
//    val diamondCode: String,
//    val digitalId: Int,
//    val ean: String,
//    val events: EventsX,
//    val format: String,
//    val id: Int,
//    val images: List<Image>,
//    val isbn: String,
//    val issn: String,
//    val issueNumber: Int,
//    val modified: String,
//    val pageCount: Int,
//    val prices: List<PriceX>,
//    val resourceURI: String,
//    val series: SeriesX,
//    val stories: StoriesX,
//    val textObjects: List<TextObject>,
//    val upc: String,
//    val urls: List<UrlX>,
//    val variantDescription: String,
//    val variants: List<Any>
) : Parcelable