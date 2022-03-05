package com.marvel.radwa.data.entities.responses

data class Data<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)