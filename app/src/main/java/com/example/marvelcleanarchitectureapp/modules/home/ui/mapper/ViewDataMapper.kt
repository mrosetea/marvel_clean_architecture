package com.example.marvelcleanarchitectureapp.modules.home.ui.mapper

import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData

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