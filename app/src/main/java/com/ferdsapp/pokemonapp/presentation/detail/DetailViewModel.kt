package com.ferdsapp.pokemonapp.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.domain.model.PokemonDetailDataEntity
import com.ferdsapp.pokemonapp.domain.useCase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor( private val pokemonUseCase: PokemonUseCase): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<PokemonDetailDataEntity>> = MutableStateFlow(
        UiState.Loading)

    val uiState : StateFlow<UiState<PokemonDetailDataEntity>>
        get() = _uiState

    fun getPokemonDetailData(id: String) {
        Log.d("DetailViewModel", "getPokemonDetailData: id $id")
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            pokemonUseCase.getPokemonDetailData(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { detailEntity ->
                    when(detailEntity){
                        is UiState.Error -> {
                            _uiState.value = UiState.Error(detailEntity.errorMessage)
                        }
                        is UiState.Loading -> {
                            _uiState.value = UiState.Loading
                        }
                        is UiState.Success -> {
                            _uiState.value = UiState.Success(detailEntity.data)
                        }
                    }

                }
        }
    }
}