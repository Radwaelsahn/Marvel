package com.marvel.radwa.data.local.db

import androidx.room.*
import com.marvel.radwa.data.local.models.CharacterLocal

import com.marvel.radwa.data.local.models.ComicsLocal

@Dao
interface ComicsDao {

    @Query("SELECT * FROM `comics.db`")
    fun getAll(): List<ComicsLocal>

    @Query("SELECT * FROM `comics.db` where characterId =:characterId")
    fun getComicsById(characterId:Int): List<ComicsLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(entity: ComicsLocal): Long

    @Insert
    fun insert(vararg comic: ComicsLocal)


}