package com.marvel.radwa.data.local

import android.content.Context
import android.util.Log
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.entities.Comics
import com.marvel.radwa.data.local.db.MarvelDatabase
import com.marvel.radwa.data.local.mapper.CharacterLocalMapper
import com.marvel.radwa.data.local.mapper.ComicLocalMapper
import javax.inject.Inject


/**
 * Created by Radwa Elsahn on 3/3/2022
 */

class LocalRepository @Inject constructor(
    val context: Context
//    private val characterDao: MarvelDatabase
    //private val mapper: CharacterLocalMapper,
//    private val characterDao: CharactersDao
) : LocalSource {

    companion object {
        const val KEY_CHARACTERS = "KEY_CHARACTERS"
        val charatersMapper: CharacterLocalMapper = CharacterLocalMapper()
        val comicsMapper: ComicLocalMapper = ComicLocalMapper()
//        val characterDao: CharactersDao = MarvelDatabase.getInstance(context1)
//        val characterDao: CharactersDao = MarvelDatabase.getInstance(context1)
    }

    override fun saveCharacter(character: Character) {
        val characterDao = MarvelDatabase.getInstance(context)?.getCharactersDao()
        var id = characterDao?.insertReplace(charatersMapper.to(character))
        Log.e("saveCharacter", id.toString())
    }

    override fun getAllCharacters(): List<Character> {
//        val db = AppDatabase.getAppDataBase(App.context)
//        var characters = db?.CharactersDao()?.getAll()!!
        val characterDao = MarvelDatabase.getInstance(context)?.getCharactersDao()
        var characters = characterDao?.getAll()!!

        var list = mutableListOf<Character>()
        characters.map { list.add(charatersMapper.from(it)) }

        return list
    }


    override fun saveComic(comics: Comics) {
        val comicsDao = MarvelDatabase.getInstance(context)?.getComicsDao()
        var id = comicsDao?.insertReplace(comicsMapper.to(comics)!!)
        Log.e("saveCharacter", id.toString())
    }

    override fun getComicById(id: Int): List<Comics> {
        val comicsDao = MarvelDatabase.getInstance(context)?.getComicsDao()
        var comics = comicsDao?.getComicsById(id)

        var list = mutableListOf<Comics>()
        comics?.map { list.add(comicsMapper.from(it)) }

        return list
    }

}
