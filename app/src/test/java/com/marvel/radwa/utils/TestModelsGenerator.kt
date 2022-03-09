package com.marvel.radwa.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors


/**
 * Created by Radwa Elsahn on 3/8/17.
 */

class TestModelsGenerator {
    private val CharactersResponseApiPath = "characters/CharactersApiResponse.json"
    private val CharactersErrorApiPath = "No Internet Connection"
    private val CharactersListApiPath = "characters/CharactersList.json"
    private val ComicsResponseApiPath = "comics/Comic1017100.json"
    val typeCharacterList = object : TypeToken<List<Character>>() {}.type
    fun generateCharactersList() =
        Gson().fromJson<List<Character>>(getJson(CharactersListApiPath), typeCharacterList)!!

    val typeCharactersResponse = object : TypeToken<BaseResponse<Character>>() {}.type
    fun generateCharactersResponse() =
        Gson().fromJson<BaseResponse<Character>>(getJson(CharactersResponseApiPath), typeCharactersResponse)!!

    fun generateErrorForCharacters() = CharactersErrorApiPath
//        Gson().fromJson(getJson(CharactersErrorApiPath), ErrorResponse::class.java)!!

    val typeComicsResponse = object : TypeToken<BaseResponse<Comics>>() {}.type
    fun generateComicsResponse() =
        Gson().fromJson<BaseResponse<Comics>>(getJson(CharactersResponseApiPath), typeComicsResponse)!!

    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return JSON String from file at the given path
     */

    private fun getJson(path: String): String =
        Files.lines(Paths.get("src/test/resources/$path")).parallel().collect(Collectors.joining())
}
