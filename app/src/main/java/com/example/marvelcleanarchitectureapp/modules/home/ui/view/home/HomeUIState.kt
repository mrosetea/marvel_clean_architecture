package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData

data class HomeUIState(
    val viewData: ViewData,
    val isLoading: Boolean,
    val error: String?,
)