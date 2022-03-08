package com.marvel.radwa.data.local.db

import androidx.room.*
import com.marvel.radwa.data.local.models.CharacterLocal

@Dao
interface CharactersDao {
    @Query("SELECT * FROM `characters.db` ")
    fun getAll(): List<CharacterLocal>

//    @Query("SELECT * FROM `characters.db` WHERE id IN (:ids)")
//    fun loadAllByIds(ids: IntArray): List<CharacterLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(entity: CharacterLocal): Long

//    @Query("SELECT * FROM `characters.db` WHERE id= :id")
//    fun findById(id: Int): CharacterLocal

    @Insert
    fun insert(vararg character: CharacterLocal)

//    @Delete
//    fun delete(character: CharacterLocal)
//
//    @Query("DELETE FROM `characters.db`")
//    fun deleteAll()


}