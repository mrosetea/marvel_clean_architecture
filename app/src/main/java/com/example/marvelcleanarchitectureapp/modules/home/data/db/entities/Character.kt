package com.example.marvelcleanarchitectureapp.modules.home.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val id: Int,
    val name: String
)