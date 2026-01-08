package com.ferdsapp.pokemonapp.domain.useCase

import androidx.paging.PagingData
import com.dicoding.jetreward.ui.common.UiState
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import com.ferdsapp.pokemonapp.domain.model.ElementTypeEntity
import com.ferdsapp.pokemonapp.domain.model.PokemonCardEntity
import com.ferdsapp.pokemonapp.domain.repository.IPokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonInteractor @Inject constructor(private val pokemonRepository: IPokemonRepository): PokemonUseCase {
    override suspend fun getElementType(): Flow<UiState<ElementTypeEntity>> {
        return pokemonRepository.getElementType()
    }

    override suspend fun getAllPokemonCards(q: String?): Flow<PagingData<PokemonCardEntity>> {
        return pokemonRepository.getAllPokemonCards(q = q)
    }
}