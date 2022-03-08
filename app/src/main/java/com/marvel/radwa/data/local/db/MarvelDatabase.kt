package com.marvel.radwa.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marvel.radwa.data.local.models.CharacterLocal


@Database(entities = arrayOf(CharacterLocal::class), version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    companion object {
        var INSTANCE: MarvelDatabase? = null
        var DATABASE_NAME = "marvelDb"

        fun getInstance(context: Context): MarvelDatabase? {
            if (INSTANCE == null) {
                synchronized(MarvelDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MarvelDatabase::class.java,
                        DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }

    }
    abstract fun CharactersDao(): CharactersDao
}