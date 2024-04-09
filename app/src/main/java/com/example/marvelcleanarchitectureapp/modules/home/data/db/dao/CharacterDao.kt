package com.example.marvelcleanarchitectureapp.modules.home.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("DELETE FROM characters")
    suspend fun deleteAll()

    @Query("select * from characters")
    fun getCharacters(): List<Character>

    @Query("select * from characters")
    fun observeCharacters(): Flow<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Upsert
    suspend fun upsert(character: Character)

    @Insert
    suspend fun insertAll(characters: List<Character>)
}