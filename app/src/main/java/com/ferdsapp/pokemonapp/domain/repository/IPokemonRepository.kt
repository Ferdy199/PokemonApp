package com.ferdsapp.pokemonapp.domain.repository

import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    suspend fun getElementType(): Flow<UiState<ElementTypeEntity>>
}