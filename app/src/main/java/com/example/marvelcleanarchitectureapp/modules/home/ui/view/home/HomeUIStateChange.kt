package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data

sealed interface HomeUIStateChange {

    fun toUiState(previousState: HomeUIState): HomeUIState

    class AddHomeLoading(
        val isLoading: Boolean = true,
    ): HomeUIStateChange {
        override fun toUiState(previousState: HomeUIState): HomeUIState
                = previousState.copy(
            isLoading = this.isLoading,
        )
    }

    class RemoveHomeLoading(
        val isLoading: Boolean = false
    ): HomeUIStateChange {
        override fun toUiState(previousState: HomeUIState): HomeUIState
                = previousState.copy(
            isLoading = false,
        )
    }

    class AddCharactersList(
        val viewData: Data = Data(
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
        private val viewData: Data = Data(
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