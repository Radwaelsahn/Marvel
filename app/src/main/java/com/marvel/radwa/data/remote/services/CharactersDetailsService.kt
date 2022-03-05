package com.marvel.radwa.data.remote.services

import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.responses.BaseResponse
import com.marvel.radwa.data.remote.Urls
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Radwa Elsahn on 3/24/2020
 */

interface getCharacterDetailsService {
    @GET(Urls.CHARACTER_DETAILS)
    suspend fun getCharacterDetails(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): Response<BaseResponse<Character>>

}
