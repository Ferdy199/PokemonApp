package com.ferdsapp.pokemonapp.domain.useCase

import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import kotlinx.coroutines.flow.Flow

interface PokemonUseCase {
    suspend fun getElementType(): Flow<UiState<ElementTypeEntity>>
}