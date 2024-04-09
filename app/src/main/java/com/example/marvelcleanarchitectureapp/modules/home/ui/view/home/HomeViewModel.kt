package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcleanarchitectureapp.modules.home.data.gateway.HomeGateway
import com.example.marvelcleanarchitectureapp.modules.home.data.model.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val homeGateway: HomeGateway) : ViewModel() {

    private val initialState = Data(
        limit = 20,
        offset = 0,
        characters = emptyList()
    )
    private var uiState = HomeUIState(
        viewData = initialState,
        error = null,
        isLoading = false
    )

    private val _uiStateChange = MutableStateFlow<HomeUIStateChange>(HomeUIStateChange.None())
    val uiStateChange = _uiStateChange.asStateFlow()

    private fun updateUiState(uiStateChange: HomeUIStateChange) {
        uiState = uiStateChange.toUiState(uiState)
        _uiStateChange.update {
            uiStateChange
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            updateUiState(HomeUIStateChange.AddHomeLoading())
            updateUiState(HomeUIStateChange.RemoveHomeLoading())
            val result = homeGateway.getCharacters()

            result.onSuccess {

                updateUiState(
                    HomeUIStateChange.AddCharactersList(
                        result.result ?: Data(
                            limit = 0,
                            offset = 20,
                            characters = emptyList()
                        )
                    )
                )
            }
            result.onFailure {
                updateUiState(HomeUIStateChange.RemoveHomeLoading())
                updateUiState(
                    HomeUIStateChange.AddHomeError(
                        error = "Error al cargar los datos"
                    )
                )
            }
        }
    }

}