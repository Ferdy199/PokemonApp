package com.ferdsapp.pokemonapp.domain.useCase

import androidx.paging.PagingData
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    suspend fun getElementType(): Flow<UiState<ElementTypeEntity>>
    suspend fun getAllPokemonCards(q: String? = null): Flow<PagingData<PokemonCardEntity>>
}