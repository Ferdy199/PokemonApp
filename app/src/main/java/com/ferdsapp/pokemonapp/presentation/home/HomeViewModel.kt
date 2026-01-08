package com.ferdsapp.pokemonapp.presentation.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.useCase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<ElementTypeEntity>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ElementTypeEntity>>
        get() = _uiState

    fun getElementType(){
        viewModelScope.launch {
            pokemonUseCase.getElementType()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { elementType ->
                    when(elementType) {
                        is UiState.Error -> {
                            _uiState.value = UiState.Error(elementType.errorMessage)
                        }
                        is UiState.Loading -> {
                            _uiState.value = UiState.Loading
                        }
                        is UiState.Success -> {
                            _uiState.value = UiState.Success(elementType.data)
                        }
                    }
                }
        }
    }
}