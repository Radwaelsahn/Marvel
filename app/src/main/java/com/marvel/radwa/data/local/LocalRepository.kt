package com.marvel.radwa.data.local

import android.content.Context
import android.util.Log
import com.marvel.radwa.data.entities.Character
import com.marvel.radwa.data.local.db.MarvelDatabase
import com.marvel.radwa.data.local.mapper.CharacterLocalMapper
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
        val mapper: CharacterLocalMapper = CharacterLocalMapper()
//        val characterDao: CharactersDao = MarvelDatabase.getInstance(context1)
//        val characterDao: CharactersDao = MarvelDatabase.getInstance(context1)
    }

    override fun saveCharacter(character: Character) {
        val characterDao = MarvelDatabase.getInstance(context)?.CharactersDao()
        var size = characterDao?.insertReplace(mapper.to(character))
        Log.e("saveCharacter",size.toString())
    }

    override fun getAllCharacters(): List<Character> {
//        val db = AppDatabase.getAppDataBase(App.context)
//        var characters = db?.CharactersDao()?.getAll()!!
        val characterDao = MarvelDatabase.getInstance(context)?.CharactersDao()
        var characters = characterDao?.getAll()!!

        var list = mutableListOf<Character>()
        characters.map { list.add(mapper.from(it)) }

        return list
    }


}
