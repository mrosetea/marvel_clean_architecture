package com.example.marvelcleanarchitectureapp.modules.home.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character

@Dao
interface CharacterDao {

    @Query("DELETE FROM characters")
    suspend fun deleteAll()

    @Query("select * from characters")
    fun getCharacters(): List<Character>

    @Insert
    suspend fun insert(character: Character)

    @Insert
    suspend fun insertAll(characters: List<Character>)
}