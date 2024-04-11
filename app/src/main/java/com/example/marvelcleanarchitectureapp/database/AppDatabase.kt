package com.example.marvelcleanarchitectureapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelcleanarchitectureapp.modules.home.data.db.dao.CharacterDao
import com.example.marvelcleanarchitectureapp.modules.home.data.db.entities.Character

@Database(entities = [Character::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

}