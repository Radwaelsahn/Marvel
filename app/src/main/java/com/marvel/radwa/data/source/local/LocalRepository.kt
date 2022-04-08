package com.marvel.radwa.data.source.local

import android.content.Context
import android.util.Log
import com.marvel.radwa.data.models.Character
import com.marvel.radwa.data.models.Comics
import com.marvel.radwa.data.source.local.db.MarvelDatabase
import com.marvel.radwa.data.source.local.mapper.CharacterLocalMapper
import com.marvel.radwa.data.source.local.mapper.ComicsLocalMapper
import javax.inject.Inject


/**
 * Created by Radwa Elsahn on 3/3/2022
 */

class LocalRepository @Inject constructor(
    val context: Context,
    val charatersMapper: CharacterLocalMapper,
    val comicsMapper: ComicsLocalMapper
) : LocalSource {

    override suspend fun saveCharacter(character: Character) {
        val characterDao = MarvelDatabase.getInstance(context)?.getCharactersDao()
        var id = characterDao?.insertReplace(charatersMapper.to(character))
        Log.e("saveCharacter", id.toString())
    }

    override suspend fun getAllCharacters(): List<Character> {
//        val db = AppDatabase.getAppDataBase(App.context)
//        var characters = db?.CharactersDao()?.getAll()!!
        val characterDao = MarvelDatabase.getInstance(context)?.getCharactersDao()
        var characters = characterDao?.getAll()!!

        var list = mutableListOf<Character>()
        characters.map { list.add(charatersMapper.from(it)) }

        return list
    }


    override suspend fun saveComic(comics: Comics) {
        val comicsDao = MarvelDatabase.getInstance(context)?.getComicsDao()
        var id = comicsDao?.insertReplace(comicsMapper.to(comics)!!)
        Log.e("saveComic", id.toString())
    }

    override suspend fun getComicById(id: Int): List<Comics> {
        val comicsDao = MarvelDatabase.getInstance(context)?.getComicsDao()
        var comics = comicsDao?.getComicsById(id)

        var list = mutableListOf<Comics>()
        comics?.map { list.add(comicsMapper.from(it)) }

        return list
    }

}
