package com.marvel.radwa.data.error

/**
 * Created by Radwa Elsahn on 7/7/2020
 */

class Error(val code: Int, val description: String) {
    constructor(exception: Exception) : this(code = DEFAULT_ERROR, description = exception.message
            ?: "")

    companion object {
        const val NO_INTERNET_CONNECTION = -1
        const val NETWORK_ERROR = -2
        const val DEFAULT_ERROR = -3
    }
}