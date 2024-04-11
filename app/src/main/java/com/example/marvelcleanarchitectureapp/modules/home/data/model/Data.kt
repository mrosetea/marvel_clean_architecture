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

fun Data.toViewData(): ViewData = ViewData(
    limit = limit,
    offset = offset,
    characters = characters.map {
        ViewData.Character(
            it.id,
            it.name,
            it.urlImage
        )
    }
)