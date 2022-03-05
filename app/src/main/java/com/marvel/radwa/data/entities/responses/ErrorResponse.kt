package com.marvel.radwa.data.entities.responses

data class ErrorResponse(
    val message: String?,
    var errors: LinkedHashMap<String, List<String>>?
)


