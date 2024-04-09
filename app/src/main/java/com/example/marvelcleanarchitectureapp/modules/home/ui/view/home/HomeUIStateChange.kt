package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData

sealed interface HomeUIStateChange {

    fun toUiState(previousState: HomeUIState): HomeUIState

    class Loading(
        val isLoading: Boolean,
    ): HomeUIStateChange {
        override fun toUiState(previousState: HomeUIState): HomeUIState
                = previousState.copy(
            isLoading = this.isLoading,
        )
    }

    class AddCharactersList(
        val viewData: ViewData = ViewData(
            0,
            20,
            characters = emptyList()
        ),
    ): HomeUIStateChange {
        override fun toUiState(previousState: HomeUIState): HomeUIState
                = previousState.copy(
            viewData = this.viewData,
        )
    }

    class AddHomeError(
        val error: String,
        private val isLoading: Boolean = false,
        val viewData: ViewData = ViewData(
            0,
            20,
            characters = emptyList()
        )
    ): HomeUIStateChange{
        override fun toUiState(previousState: HomeUIState): HomeUIState
                = previousState.copy(
            error = this.error,
            isLoading = this.isLoading,
            viewData = this.viewData
        )
    }

    class None: HomeUIStateChange {
        override fun toUiState(previousState: HomeUIState): HomeUIState
                = previousState
    }

}