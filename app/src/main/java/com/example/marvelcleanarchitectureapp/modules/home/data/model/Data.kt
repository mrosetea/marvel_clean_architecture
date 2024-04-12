package com.example.marvelcleanarchitectureapp.modules.home.data.model

import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData

class Data(
    val offset: Int,
    val limit: Int,
    val characters: List<Character>,
    val error: String? = null,
) {
    class Character(
        val id: Int,
        val name: String,
        val urlImage: String,
    )
}

