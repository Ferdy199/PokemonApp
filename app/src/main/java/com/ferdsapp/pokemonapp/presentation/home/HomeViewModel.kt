package com.ferdsapp.pokemonapp.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity
import com.ferdsapp.pokemonapp.domain.useCase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val pokemonUseCase: PokemonUseCase): ViewModel() {

    //get Type
    private val _uiState: MutableStateFlow<UiState<ElementTypeEntity>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ElementTypeEntity>>
        get() = _uiState

    // get all cards
    private val _getAllCardsUiState: MutableStateFlow<PagingData<PokemonCardEntity>> =
        MutableStateFlow(PagingData.empty())

    val getAllCardsUiState: StateFlow<PagingData<PokemonCardEntity>>
        get() = _getAllCardsUiState

    // Search card
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val searchState: StateFlow<PagingData<PokemonCardEntity>> =
        _query.debounce(300)
            .map { it.trim() }
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isBlank()){
                    flowOf(PagingData.empty())
                }else{
                    pokemonUseCase.getAllPokemonCards(q = "name:$query")
                }
            }
            .cachedIn(viewModelScope)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PagingData.empty()
            )

    fun onQueryChanged(newQuery: String){
        _query.value = newQuery
    }

    fun searchNow(query: String) {
        _query.value = query
    }

    fun getElementType() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading

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

    fun getAllPokemonCards(q: String? = null) {
        Log.d("ViewModel", "getAllPokemonCards type: $q")
        _getAllCardsUiState.value = PagingData.empty()
        viewModelScope.launch {
            pokemonUseCase.getAllPokemonCards("types:$q")
                .cachedIn(viewModelScope)
                .collect {
                    _getAllCardsUiState.value = it
                }
        }
    }
}