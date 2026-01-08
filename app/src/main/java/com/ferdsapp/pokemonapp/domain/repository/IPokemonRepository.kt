package com.ferdsapp.pokemonapp.domain.repository

import androidx.paging.PagingData
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    suspend fun getElementType(): Flow<UiState<ElementTypeEntity>>
    suspend fun getAllPokemonCards(): Flow<PagingData<PokemonCardEntity>>
}