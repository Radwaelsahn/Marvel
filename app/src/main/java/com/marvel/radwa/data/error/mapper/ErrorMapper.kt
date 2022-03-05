package com.marvel.radwa.data.error.mapper


import com.marvel.radwa.App
import com.marvel.radwa.R
import com.marvel.radwa.data.error.Error
import javax.inject.Inject


class ErrorMapper @Inject constructor() : ErrorMapperInterface {

    override fun getErrorString(errorId: Int): String {
        return App.context.resources.getString(errorId)

    }

    override val errorsMap: Map<Int, String>
        get() = mapOf(
                Pair(Error.NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet)),
                Pair(Error.NETWORK_ERROR, getErrorString(R.string.network_error))
        ).withDefault { getErrorString(R.string.network_error) }
}