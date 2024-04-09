package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data

data class HomeUIState(
    val viewData: Data,
    val isLoading: Boolean,
    val error: String?,
)