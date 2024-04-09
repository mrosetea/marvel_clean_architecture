package com.example.marvelcleanarchitectureapp.modules.home.ui.model

class ViewData(
    val limit: Int,
    val offset: Int,
    val characters: List<Character>
) {
    data class Character(
        val id: Int,
        val name: String,
    )
}

