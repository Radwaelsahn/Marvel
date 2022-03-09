/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marvel.radwa.data.source

import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.models.responses.BaseResponse
import com.marvel.radwa.data.source.local.LocalSource

//class FakeDataSource(var characters: MutableList<Character> = mutableListOf()) : DataSource {
class FakeLocalSource(var charactersResponseStr: String) : LocalSource {

    override fun getAllCharacters(): List<Character> {

        return try {
            var response = charactersResponseStr as BaseResponse<Character>
            response?.data?.results.toList()
        } catch (e: Exception) {
            listOf()
        }
    }

    override fun saveCharacter(character: Character) {
    }


    override fun saveComic(comics: Comics) {

    }

    override fun getComicById(id: Int): List<Comics> {
        return listOf()
    }


}