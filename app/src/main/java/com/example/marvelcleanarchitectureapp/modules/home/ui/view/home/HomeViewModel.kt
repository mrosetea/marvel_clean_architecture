package com.example.marvelcleanarchitectureapp.modules.home.ui.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelcleanarchitectureapp.modules.home.data.gateway.HomeGateway
import com.example.marvelcleanarchitectureapp.modules.home.data.model.toViewData
import com.example.marvelcleanarchitectureapp.modules.home.ui.model.ViewData
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val LIMIT = 20

class HomeViewModel(private val homeGateway: HomeGateway) : ViewModel() {

    private val initialState = ViewData(
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

    init {
        fetchCharacters(0)
    }

    private fun updateUiState(uiStateChange: HomeUIStateChange) {
        uiState = uiStateChange.toUiState(uiState)
        _uiStateChange.update {
            uiStateChange
        }
    }

    fun fetchCharacters(offset: Int){
        viewModelScope.launch {
            updateUiState(HomeUIStateChange.Loading(true))
            val result = homeGateway.getCharacters(offset, LIMIT)
            updateUiState(HomeUIStateChange.Loading(false))
            delay(10)
            result.onSuccess {
                val state = when {
                    it.error != null -> HomeUIStateChange.AddHomeError(
                        error = it.error,
                        viewData = it.toViewData()
                    )

                    else -> HomeUIStateChange.AddCharactersList(
                        it.toViewData()
                    )
                }
                updateUiState(state)
            }

            result.onFailure {
                updateUiState(
                    HomeUIStateChange.AddHomeError(
                        error = "Error al cargar los datos"
                    )
                )
            }
        }
    }





}