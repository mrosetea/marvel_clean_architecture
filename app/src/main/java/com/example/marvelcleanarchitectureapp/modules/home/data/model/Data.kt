package com.example.marvelcleanarchitectureapp.modules.home.data.model

class Data(
    val offset: Int,
    val limit: Int,
    val characters: List<Character>
) {
    class Character(
        val id: Int,
        val name: String
    )
}