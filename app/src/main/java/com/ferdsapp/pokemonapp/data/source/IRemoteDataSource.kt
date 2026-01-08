package com.ferdsapp.pokemonapp.data.source

import com.ferdsapp.pokemonapp.data.model.elementType.ElementTypeResponses
import com.ferdsapp.pokemonapp.data.model.pokemonCard.PokemonCardResponse
import com.ferdsapp.pokemonapp.data.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
    suspend fun getElementType(): Flow<ApiResponse<ElementTypeResponses>>
    suspend fun getAllPokemonCards(page: Int? = 1, q: String? = null): PokemonCardResponse
}